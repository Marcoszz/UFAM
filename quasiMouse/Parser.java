

import java.lang.*;
import java.io.*;



public class Parser {
	public static final int _EOF = 0;
	public static final int _id = 1;
	public static final int _strConst = 2;
	public static final int _num = 3;
	public static final int maxT = 28;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	private Tab ts;

private Obj ofuncAtual;

private Code objCode;



	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void quasiMouse() {
		ofuncAtual = null; 
		ts = new Tab(this);
		ts.abrirEscopo("Global");
		objCode = new Code();
		int opr = -1; 
		
		while (la.kind == 4) {
			DeclFuncao();
		}
		ts.abrirEscopo("main");
		objCode.setMainPC();
		objCode.put(objCode.enter);
		objCode.put(0);
		objCode.put(26);
		
		opr = Instrucao();
		while (StartOf(1)) {
			opr = Instrucao();
		}
		Expect(4);
		objCode.put(objCode.return_);
		objCode.put(objCode.exit);
		ts.fecharEscopo(); //main
		objCode.setDataSize(ts.escopoAtual.nVars);
		ts.fecharEscopo(); //global
		
	}

	void DeclFuncao() {
		Expect(4);
		Expect(1);
		ofuncAtual = ts.inserir(Obj.Func, t.val, Tab.semTipo);
		ts.abrirEscopo("Func  " + t.val);
		int opr = -1;
		
		while (la.kind == 5) {
			Get();
			Expect(1);
			ts.inserir(Obj.Var, t.val, Tab.tipoInt);
			ofuncAtual.nPars ++; 
			
		}
		Expect(6);
		ofuncAtual.end = objCode.getPC();
		objCode.put(objCode.enter);
		objCode.put(ofuncAtual.nPars);
		objCode.put(26);
		
		while (StartOf(1)) {
			opr = Instrucao();
		}
		Expect(7);
		objCode.put(objCode.exit);
		objCode.put(objCode.return_);
		ofuncAtual.locais = ts.escopoAtual.locais;
		ts.fecharEscopo();
		
	}

	int  Instrucao() {
		int  opr;
		opr = -1; 
		switch (la.kind) {
		case 19: {
			FunCall();
			break;
		}
		case 1: case 3: {
			Assinalamento();
			break;
		}
		case 20: case 21: case 22: {
			opr = Bloco();
			break;
		}
		case 2: case 16: case 17: {
			Printf();
			break;
		}
		case 18: {
			Scanf();
			break;
		}
		case 13: {
			While();
			break;
		}
		case 23: case 24: case 25: case 26: case 27: {
			OpAri();
			break;
		}
		default: SynErr(29); break;
		}
		return opr;
	}

	void FunCall() {
		Expect(19);
		Expect(1);
		Obj ops = ts.buscar(t.val);
		objCode.put(objCode.call);
		objCode.put2(ops.end);
		ops.cat = Operand.Stack;
		
	}

	void Assinalamento() {
		if (la.kind == 1) {
			Get();
			Obj n = ts.buscar(t.val);
			
			if (la.kind == 8) {
				Get();
				objCode.load(new Operand(n)); 
				
			} else if (la.kind == 9) {
				Get();
				Obj ops = Tab.semObj;
				for( Obj aux = Tab.escopoAtual.locais; aux != null; aux = aux.prox){
				 if(aux.nome.equals(n.nome)){
				   ops = aux;
				   break;
				 }
				}
				if(ops == Tab.semObj) ops = ts.inserir(Obj.Var, n.nome, Tab.tipoInt);
				objCode.store(new Operand(ops));
				
			} else SynErr(30);
		} else if (la.kind == 3) {
			Get();
			objCode.load(new Operand(Integer.parseInt(t.val))); 
			
		} else SynErr(31);
	}

	int  Bloco() {
		int  opr;
		int nOpr = -1;
		opr = objCode.eq;
		
		opr = OpRel();
		if (la.kind == 10) {
			Get();
			int n_end = objCode.putFalseJump(opr,0); 
			
			while (StartOf(1)) {
				nOpr = Instrucao();
			}
			if (la.kind == 11) {
				Get();
				int n_end2 = objCode.putJump(0); 
				objCode.fixup(n_end); 
				
				while (StartOf(1)) {
					nOpr = Instrucao();
				}
				objCode.fixup(n_end2); 
				
			} else if (la.kind == 12) {
				objCode.fixup(n_end); 
				
			} else SynErr(32);
			Expect(12);
		}
		return opr;
	}

	void Printf() {
		if (la.kind == 2) {
			Get();
			objCode.putPrintStrz(t.val.substring(1,t.val.length()-1)); 
			
		} else if (la.kind == 16) {
			Get();
			objCode.put(objCode.printi); 
			
		} else if (la.kind == 17) {
			Get();
			objCode.putPrintStrz("\n"); 
			
		} else SynErr(33);
	}

	void Scanf() {
		Expect(18);
		objCode.put(objCode.scani); 
		
	}

	void While() {
		int opr, end; 
		
		Expect(13);
		int head = objCode.getPC(); 
		
		opr = Instrucao();
		while (StartOf(1)) {
			opr = Instrucao();
		}
		Expect(14);
		end = objCode.putFalseJump(opr, 0); 
		
		if (StartOf(1)) {
			opr = Instrucao();
			while (StartOf(1)) {
				opr = Instrucao();
			}
		} else if (la.kind == 15) {
		} else SynErr(34);
		Expect(15);
		objCode.putJump(head); 
		objCode.fixup(end); 
		
	}

	void OpAri() {
		if (la.kind == 23) {
			Get();
			objCode.put(objCode.add); 
			
		} else if (la.kind == 24) {
			Get();
			objCode.put(objCode.sub);
			
		} else if (la.kind == 25) {
			Get();
			objCode.put(objCode.div); 
			
		} else if (la.kind == 26) {
			Get();
			objCode.put(objCode.mul); 
			
		} else if (la.kind == 27) {
			Get();
			objCode.put(objCode.rem); 
			
		} else SynErr(35);
	}

	int  OpRel() {
		int  opr;
		opr = -1; 
		
		if (la.kind == 20) {
			Get();
			opr = objCode.eq; 
			
		} else if (la.kind == 21) {
			Get();
			opr = objCode.gt; 
			
		} else if (la.kind == 22) {
			Get();
			opr = objCode.lt; 
			
		} else SynErr(36);
		return opr;
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		quasiMouse();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x},
		{x,T,T,T, x,x,x,x, x,x,x,x, x,T,x,x, T,T,T,T, T,T,T,T, T,T,T,T, x,x}

	};
} // end Parser


class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text
	
	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}
	
	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "id expected"; break;
			case 2: s = "strConst expected"; break;
			case 3: s = "num expected"; break;
			case 4: s = "\"$\" expected"; break;
			case 5: s = "\",\" expected"; break;
			case 6: s = "\";\" expected"; break;
			case 7: s = "\"@\" expected"; break;
			case 8: s = "\".\" expected"; break;
			case 9: s = "\":\" expected"; break;
			case 10: s = "\"[\" expected"; break;
			case 11: s = "\"|\" expected"; break;
			case 12: s = "\"]\" expected"; break;
			case 13: s = "\"(\" expected"; break;
			case 14: s = "\"^\" expected"; break;
			case 15: s = "\")\" expected"; break;
			case 16: s = "\"!\" expected"; break;
			case 17: s = "\"&\" expected"; break;
			case 18: s = "\"?\" expected"; break;
			case 19: s = "\"#\" expected"; break;
			case 20: s = "\"=\" expected"; break;
			case 21: s = "\">\" expected"; break;
			case 22: s = "\"<\" expected"; break;
			case 23: s = "\"+\" expected"; break;
			case 24: s = "\"-\" expected"; break;
			case 25: s = "\"/\" expected"; break;
			case 26: s = "\"*\" expected"; break;
			case 27: s = "\"%\" expected"; break;
			case 28: s = "??? expected"; break;
			case 29: s = "invalid Instrucao"; break;
			case 30: s = "invalid Assinalamento"; break;
			case 31: s = "invalid Assinalamento"; break;
			case 32: s = "invalid Bloco"; break;
			case 33: s = "invalid Printf"; break;
			case 34: s = "invalid While"; break;
			case 35: s = "invalid OpAri"; break;
			case 36: s = "invalid OpRel"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {	
		printMsg(line, col, s);
		count++;
	}
	
	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}
	
	public void Warning (int line, int col, String s) {	
		printMsg(line, col, s);
	}
	
	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
