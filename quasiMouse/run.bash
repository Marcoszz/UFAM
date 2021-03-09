FILE=$1
echo "compila "${FILE}".qm"
java Compile ${FILE}.qm 

echo "executa o codigo objeto "${FILE}".obj"
java -cp vm/VMRun ${FILE}.obj 
