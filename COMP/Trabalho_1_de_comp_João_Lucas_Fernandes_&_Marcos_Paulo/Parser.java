

import java.io.*;

class HtmlOut{
    String output;
    int indent = 0;

    public HtmlOut(){
        output = "<html>\n";
        output += "<head><title>Trabalho 1 de compiladores</title>\n";
        output += "<link rel="+"stylesheet href="+"styles.css"+ ">";
        output += "</head>";
        output += "<body>\n";
    }

    public void addIdent(){
        indent += 1;
    }

    public void decreaseIdent(){
        indent -= 1;
    }

    public void addFirstLine(String str){
        output += "<span>\n";
        output += str + "\n";
        output += "</span>\n";
        output += "<br>\n";
        output += "<br>\n";
    }

    public void addComment(String str){
        output += "<span class=" + "comments" + ">\n";
        output += str + " " + "\n";
        output += "</span>\n";
        output += "<br>\n";
        output += "<br>\n";
    }

    public String formatReserved(String str){
        String ret = "<span class=" + "instrucoes" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatOpRel(String str){
        String ret = "<span class=" + "op" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatOpAri(String str){
        String ret = "<span class=" + "op" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatFuncName(String str){
        String ret = "<span class=" + "funcao" +">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatVarName(String str){
        String ret = "<span class=" + "definidos" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatConstName(String str){
        String ret = "<span class=" + "constante" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatNumber(String str){
        String ret = "<span class=" + "numeros" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatType(String str){
        String ret = "<span class=" + "tipos" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public String formatString(String str){
        String ret = "<span class=" + "string" + ">\n";
        ret += str + "</span>\n";
        return ret;
    }

    public void addLine(String str){
        output += "<span>\n";
        for(int i =0; i < indent; i++){
            output += "&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        output += str;
        output += "</span>\n";
        output += "<br>";
    }

    public void show(){
        output += "</body>\n";
        output += "</html>\n";
        System.out.println(output);
    }
}



public class Parser {
	public static final int _EOF = 0;
	public static final int _numero = 1;
	public static final int _ident = 2;
	public static final int _strConst = 3;
	public static final int maxT = 53;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	HtmlOut html;


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
	
	void Uportugol() {
		html = new HtmlOut(); String str = ""; 
		if (la.kind == 41) {
			Comments();
		}
		Expect(4);
		str = html.formatReserved("algoritmo"); str += " "; 
		Expect(2);
		str += t.val; html.addFirstLine(str); 
		while (StartOf(1)) {
			if (la.kind == 7) {
				FunDecl();
			} else if (la.kind == 15) {
				VarDecl();
			} else if (la.kind == 12) {
				ConstDecl();
			} else {
				Comments();
			}
		}
		Expect(5);
		str = html.formatReserved("inicio"); html.addLine(str); html.addIdent(); 
		while (StartOf(2)) {
			if (StartOf(3)) {
				Cmd();
			} else if (la.kind == 15) {
				VarDecl();
			} else if (la.kind == 12) {
				ConstDecl();
			} else {
				Comments();
			}
		}
		Expect(6);
		html.decreaseIdent(); str = html.formatReserved("fim"); str+="<br>"; html.addLine(str); html.show(); 
	}

	void Comments() {
		String str = ""; 
		Expect(41);
		str = "/*"; 
		while (la.kind == 2 || la.kind == 10 || la.kind == 42) {
			if (la.kind == 2) {
				Get();
				str += t.val; 
			} else if (la.kind == 42) {
				Get();
				str += t.val; 
			} else {
				Get();
				str += t.val; str += " "; 
				if (la.kind == 2) {
					Get();
					str += t.val; 
				} else if (la.kind == 1) {
					Get();
					str += t.val; 
				} else SynErr(54);
			}
		}
		Expect(43);
		str += "*/"; html.addComment(str); 
	}

	void FunDecl() {
		String funcDecl = "", funcContent = "", type = "", str = ""; 
		Expect(7);
		funcDecl = html.formatReserved("procedimento"); funcDecl += " "; 
		Expect(2);
		funcDecl += html.formatFuncName(t.val); 
		Expect(8);
		funcDecl += "("; funcDecl += " "; 
		while (la.kind == 2 || la.kind == 10) {
			if (la.kind == 2) {
				Get();
				funcDecl += html.formatVarName(t.val); 
				Expect(9);
				funcDecl += ":"; 
				type = Tipo();
				funcDecl += type; 
			} else {
				Get();
				funcDecl += ","; funcDecl += " "; 
				Expect(2);
				funcDecl += html.formatVarName(t.val); 
				Expect(9);
				funcDecl += ":"; 
				type = Tipo();
				funcDecl += type; 
			}
		}
		Expect(11);
		funcDecl += ")"; 
		if (la.kind == 9) {
			Get();
			funcDecl += ":"; funcDecl += " "; 
			type = Tipo();
			funcDecl += type; 
		}
		html.addLine(funcDecl); 
		Expect(5);
		funcContent = html.formatReserved("inicio"); html.addLine(funcContent); html.addIdent();
		while (StartOf(2)) {
			if (StartOf(3)) {
				Cmd();
			} else if (la.kind == 15) {
				VarDecl();
			} else if (la.kind == 12) {
				ConstDecl();
			} else {
				Comments();
			}
		}
		Expect(6);
		html.decreaseIdent(); str = html.formatReserved("fim") + "<br>"; html.addLine(str); 
	}

	void VarDecl() {
		String varDleca = "", type = "";
		Expect(15);
		varDleca = html.formatReserved("variavel"); varDleca +=" "; 
		Expect(2);
		varDleca += html.formatVarName(t.val); 
		while (la.kind == 10) {
			Get();
			varDleca += ","; varDleca +=" "; 
			Expect(2);
			varDleca += html.formatVarName(t.val); 
		}
		Expect(9);
		varDleca += ":"; varDleca +=" "; 
		type = Tipo();
		varDleca += type; 
		Expect(14);
		varDleca += ";"; html.addLine(varDleca) ;
	}

	void ConstDecl() {
		String constDleca = "";
		Expect(12);
		constDleca = html.formatConstName("constante"); constDleca +=" "; 
		Expect(2);
		constDleca += t.val; constDleca +=" "; 
		Expect(13);
		constDleca += html.formatOpRel("="); constDleca +=" "; 
		Expect(1);
		constDleca += html.formatNumber(t.val); 
		Expect(14);
		constDleca += ";<br>"; html.addLine(constDleca) ;
	}

	void Cmd() {
		String identfier = "", identfier2 = "", exp1 = "", str = ""; 
		switch (la.kind) {
		case 2: {
			identfier = Identificador();
			identfier = identfier;
			if (la.kind == 13) {
				Get();
				identfier += html.formatOpRel(" = "); 
				if (StartOf(4)) {
					exp1 = Expr();
					identfier += exp1; 
				} else if (la.kind == 20) {
					Get();
					identfier += html.formatReserved("novo");
					Expect(16);
					identfier += html.formatReserved("inteiro");
					if (la.kind == 18) {
						Get();
						identfier += "[";
						identfier2 = Identificador();
						identfier += identfier2;
						Expect(19);
						identfier += "]";
					} else if (la.kind == 21) {
						Get();
						identfier += "{";
						Expect(1);
						identfier += html.formatNumber(t.val);
						while (la.kind == 10) {
							Get();
							identfier += ","; identfier += " ";
							Expect(1);
							identfier += html.formatNumber(t.val);
						}
						Expect(22);
						identfier += "}";
					} else SynErr(55);
				} else SynErr(56);
			}
			Expect(14);
			identfier += t.val; html.addLine(identfier); 
			break;
		}
		case 23: {
			Get();
			str = html.formatReserved("retorne"); exp1 = ""; 
			if (StartOf(4)) {
				exp1 = Expr();
				str += exp1; 
			}
			Expect(14);
			str += ";"; html.addLine(str); 
			break;
		}
		case 24: {
			Get();
			str = html.formatReserved("escreva") + "("; 
			if (la.kind == 3) {
				Get();
				str += html.formatString(t.val); 
			} else if (la.kind == 2) {
				identfier = Identificador();
				str += identfier; 
			} else SynErr(57);
			while (la.kind == 10) {
				Get();
				str += ","; str += " "; 
				if (la.kind == 2) {
					identfier2 = Identificador();
					str += identfier2; 
				} else if (la.kind == 3) {
					Get();
					str += html.formatString(t.val); 
				} else SynErr(58);
			}
			Expect(11);
			str += ")"; 
			Expect(14);
			str += ";"; html.addLine(str); 
			break;
		}
		case 25: {
			Get();
			str = html.formatReserved("caso"); String identfier1 = ""; 
			identfier1 = Identificador();
			str += identfier1;html.addLine(str); html.addIdent();
			Expect(26);
			str = html.formatReserved("seja"); 
			Expect(1);
			str += html.formatNumber(t.val); 
			Expect(27);
			str += html.formatReserved("faca"); html.addLine(str); html.addIdent(); 
			while (StartOf(3)) {
				Cmd();
			}
			while (la.kind == 26) {
				Get();
				str = html.formatReserved("seja"); html.decreaseIdent(); 
				Expect(1);
				str += html.formatNumber(t.val); 
				Expect(27);
				str += html.formatReserved("faca"); html.addLine(str); html.addIdent(); 
				while (StartOf(3)) {
					Cmd();
				}
			}
			Expect(28);
			str = html.formatReserved("outrocaso:"); html.decreaseIdent(); html.addLine(str); html.addIdent();
			while (StartOf(3)) {
				Cmd();
			}
			Expect(29);
			str = html.formatReserved("fimcaso:"); html.decreaseIdent(); html.decreaseIdent(); html.addLine(str);
			if (la.kind == 14) {
				Get();
				str += ";"; 
			}
			html.addLine(str); 
			break;
		}
		case 30: {
			Get();
			str = html.formatReserved("se"); exp1 = ""; 
			exp1 = Expr();
			str += exp1; 
			Expect(31);
			str +=  html.formatReserved("entao"); html.addLine(str); html.addIdent(); 
			while (StartOf(3)) {
				Cmd();
			}
			if (la.kind == 32) {
				Get();
				str =  html.formatReserved(" senao "); html.addLine(str); html.addIdent(); 
				while (StartOf(3)) {
					Cmd();
				}
				html.decreaseIdent();
			}
			Expect(33);
			str =  html.formatReserved(" fimse "); html.decreaseIdent(); 
			if (la.kind == 14) {
				Get();
				str += ";"; 
			}
			html.addLine(str); 
			break;
		}
		case 34: {
			Get();
			str = html.formatReserved(" enquanto "); exp1 = ""; 
			exp1 = Expr();
			str += exp1; 
			Expect(27);
			str +=  html.formatReserved(" faca "); html.addLine(str); html.addIdent(); 
			while (StartOf(3)) {
				Cmd();
			}
			Expect(35);
			str =  html.formatReserved(" fimenquanto "); html.decreaseIdent(); 
			if (la.kind == 14) {
				Get();
				str += ";"; 
			}
			html.addLine(str); 
			break;
		}
		case 36: {
			Get();
			str = html.formatReserved(" para "); exp1 = ""; String exp2 = "", exp3 = ""; 
			exp1 = Expr();
			str += exp1; 
			Expect(13);
			str += html.formatOpRel(" = "); 
			exp2 = Expr();
			str += exp2; 
			Expect(37);
			str += " ate "; 
			exp3 = Expr();
			str += exp3; 
			if (la.kind == 38) {
				Get();
				str += html.formatReserved(" passo "); 
				Expect(1);
				str += html.formatNumber(t.val); 
			}
			Expect(27);
			str += html.formatReserved(" faca "); html.addLine(str); html.addIdent();
			while (StartOf(3)) {
				Cmd();
			}
			Expect(39);
			str =  html.formatReserved(" fimpara"); html.decreaseIdent(); 
			if (la.kind == 14) {
				Get();
				str += ";"; 
			}
			html.addLine(str); 
			break;
		}
		case 40: {
			Get();
			str = html.formatReserved(" repita "); exp1 = ""; html.addLine(str); html.addIdent(); 
			while (StartOf(3)) {
				Cmd();
			}
			Expect(37);
			str = html.formatReserved("ate"); html.decreaseIdent(); 
			exp1 = Expr();
			str += exp1; 
			Expect(14);
			str += ";"; html.addLine(str); 
			break;
		}
		default: SynErr(59); break;
		}
	}

	String  Tipo() {
		String  type;
		type = "";
		if (la.kind == 16) {
			Get();
			type = html.formatType("inteiro");
		} else if (la.kind == 17) {
			Get();
			type = html.formatType("inteiro[]");
		} else SynErr(60);
		return type;
	}

	String  Identificador() {
		String  identfier;
		identfier = ""; String exp1 = "", exp2 = "", exp3 = ""; 
		Expect(2);
		identfier = " " + t.val ; 
		if (la.kind == 8 || la.kind == 18) {
			if (la.kind == 18) {
				Get();
				identfier = identfier; identfier += "["; 
				exp1 = AriExpr();
				identfier += exp1; 
				Expect(19);
				identfier += "]"; 
			} else {
				Get();
				identfier = html.formatFuncName(identfier); identfier += "("; 
				exp2 = AriExpr();
				identfier += exp2; 
				while (la.kind == 10) {
					Get();
					identfier += ","; identfier += " "; 
					exp3 = AriExpr();
					identfier += exp3; 
				}
				Expect(11);
				identfier += ")"; 
			}
		}
		return identfier;
	}

	String  AriExpr() {
		String  exp1;
		String term = "",term2 = "";
		term = Term();
		exp1 = term;
		while (la.kind == 44 || la.kind == 45) {
			if (la.kind == 44) {
				Get();
				exp1 += html.formatOpAri(" + ");
			} else {
				Get();
				exp1 += html.formatOpAri(" - ");
			}
			term2 = Term();
			exp1 += term2;
		}
		return exp1;
	}

	String  Expr() {
		String  condi;
		condi = ""; String exp1 = "", exp2 = "", op = "";
		exp1 = AriExpr();
		condi = " " + exp1;
		if (StartOf(5)) {
			op = RelOp();
			condi += " " + op; 
			exp2 = AriExpr();
			condi += " " + exp2 + " "; 
		}
		return condi;
	}

	String  RelOp() {
		String  op;
		op = "";
		if (la.kind == 48) {
			Get();
			op = html.formatOpRel("==");
		} else if (la.kind == 49) {
			Get();
			op = html.formatOpRel("<");
		} else if (la.kind == 50) {
			Get();
			op = html.formatOpRel(">");
		} else if (la.kind == 51) {
			Get();
			op = html.formatOpRel("<=");
		} else if (la.kind == 52) {
			Get();
			op = html.formatOpRel(">=");
		} else SynErr(61);
		return op;
	}

	String  Term() {
		String  term;
		term = ""; String fat = "", fat2 = "";
		fat = Fator();
		term = fat;
		while (la.kind == 42 || la.kind == 46 || la.kind == 47) {
			if (la.kind == 42) {
				Get();
				term += html.formatOpAri(" * ");
			} else if (la.kind == 46) {
				Get();
				term += html.formatOpAri(" / ");
			} else {
				Get();
				term += html.formatOpAri(" % ");
			}
			fat2 = Fator();
			term += fat2;
		}
		return term;
	}

	String  Fator() {
		String  fat;
		fat = ""; String fat2 = "", identfier = "", exp1 = "";
		if (la.kind == 2) {
			identfier = Identificador();
			fat = identfier;
		} else if (la.kind == 1) {
			Get();
			fat = html.formatNumber(t.val);
		} else if (la.kind == 45) {
			Get();
			fat = html.formatOpAri(" - ");
			fat2 = Fator();
			fat += fat2;
		} else if (la.kind == 8) {
			Get();
			fat = "(";
			exp1 = Expr();
			fat += " " + exp1;
			Expect(11);
			fat += ")";
		} else SynErr(62);
		return fat;
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Uportugol();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x},
		{x,x,x,x, x,x,x,T, x,x,x,x, T,x,x,T, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,T,x,x, x,x,x,x, x,x,x,x, x,x,x},
		{x,x,T,x, x,x,x,x, x,x,x,x, T,x,x,T, x,x,x,x, x,x,x,T, T,T,x,x, x,x,T,x, x,x,T,x, T,x,x,x, T,T,x,x, x,x,x,x, x,x,x,x, x,x,x},
		{x,x,T,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,T, T,T,x,x, x,x,T,x, x,x,T,x, T,x,x,x, T,x,x,x, x,x,x,x, x,x,x,x, x,x,x},
		{x,T,T,x, x,x,x,x, T,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,T,x,x, x,x,x,x, x,x,x},
		{x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, T,T,T,T, T,x,x}

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
			case 1: s = "numero expected"; break;
			case 2: s = "ident expected"; break;
			case 3: s = "strConst expected"; break;
			case 4: s = "\"algoritmo\" expected"; break;
			case 5: s = "\"inicio\" expected"; break;
			case 6: s = "\"fim\" expected"; break;
			case 7: s = "\"procedimento\" expected"; break;
			case 8: s = "\"(\" expected"; break;
			case 9: s = "\":\" expected"; break;
			case 10: s = "\",\" expected"; break;
			case 11: s = "\")\" expected"; break;
			case 12: s = "\"constante\" expected"; break;
			case 13: s = "\"=\" expected"; break;
			case 14: s = "\";\" expected"; break;
			case 15: s = "\"variavel\" expected"; break;
			case 16: s = "\"inteiro\" expected"; break;
			case 17: s = "\"inteiro[]\" expected"; break;
			case 18: s = "\"[\" expected"; break;
			case 19: s = "\"]\" expected"; break;
			case 20: s = "\"novo\" expected"; break;
			case 21: s = "\"{\" expected"; break;
			case 22: s = "\"}\" expected"; break;
			case 23: s = "\"retorne\" expected"; break;
			case 24: s = "\"escreva(\" expected"; break;
			case 25: s = "\"caso\" expected"; break;
			case 26: s = "\"seja\" expected"; break;
			case 27: s = "\"faca\" expected"; break;
			case 28: s = "\"outrocaso:\" expected"; break;
			case 29: s = "\"fimcaso\" expected"; break;
			case 30: s = "\"se\" expected"; break;
			case 31: s = "\"entao\" expected"; break;
			case 32: s = "\"senao\" expected"; break;
			case 33: s = "\"fimse\" expected"; break;
			case 34: s = "\"enquanto\" expected"; break;
			case 35: s = "\"fimenquanto\" expected"; break;
			case 36: s = "\"para\" expected"; break;
			case 37: s = "\"ate\" expected"; break;
			case 38: s = "\"passo\" expected"; break;
			case 39: s = "\"fimpara\" expected"; break;
			case 40: s = "\"repita\" expected"; break;
			case 41: s = "\"/*\" expected"; break;
			case 42: s = "\"*\" expected"; break;
			case 43: s = "\"*/\" expected"; break;
			case 44: s = "\"+\" expected"; break;
			case 45: s = "\"-\" expected"; break;
			case 46: s = "\"/\" expected"; break;
			case 47: s = "\"%\" expected"; break;
			case 48: s = "\"==\" expected"; break;
			case 49: s = "\"<\" expected"; break;
			case 50: s = "\">\" expected"; break;
			case 51: s = "\"<=\" expected"; break;
			case 52: s = "\">=\" expected"; break;
			case 53: s = "??? expected"; break;
			case 54: s = "invalid Comments"; break;
			case 55: s = "invalid Cmd"; break;
			case 56: s = "invalid Cmd"; break;
			case 57: s = "invalid Cmd"; break;
			case 58: s = "invalid Cmd"; break;
			case 59: s = "invalid Cmd"; break;
			case 60: s = "invalid Tipo"; break;
			case 61: s = "invalid RelOp"; break;
			case 62: s = "invalid Fator"; break;
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
