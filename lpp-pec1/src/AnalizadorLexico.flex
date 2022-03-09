/* Secci�n de declaraciones de JFlex */
%%
%public
%class AnalizadorLexico
%{
 
 /* C�digo personalizado */
 
 // Se agreg� una propiedad para verificar si existen tokens pendientes
 private boolean _existenTokens = false;
 
 public boolean existenTokens(){
 return this._existenTokens;
 }
 
%}
 
 /* Al utilizar esta instrucci�n, se le indica a JFlex que devuelva objetos del tipo TokenPersonalizado */
%type TokenInfo
 
%init{
 /* C�digo que se ejecutar� en el constructor de la clase */
%init}
 
%eof{
 
 /* C�digo a ejecutar al finalizar el an�lisis, en este caso cambiaremos el valor de una variable bandera */
 this._existenTokens = false;
 
%eof}
 
/* Inicio de Expresiones regulares */

 Comentario=--.*
 Digito = [:digit:]
 Numero = {Digito} {Digito}*
 Letra = [:letter:]
 Identificador = {Letra} [:jletterdigit:]*
 Simbolo = "*"|"+"|"-"|"/"|"#"
 Espacio = " "
 SaltoDeLinea = \n|\r|\r\n
 PalabraReservadaBool="Boolean"|"False"|"True"
 PalabraReservadaSentencia="if"|"for"|"loop"|"while"
 PalabraReservada= {PalabraReservadaBool}|{PalabraReservadaSentencia}|"and"|"array"|"begin"|"constant"|"else"|"end"|"function"|"in"|"Integer"|"is"|"of"|"or"|"out"|"procedure"|"Put_line"|"record"|"return"|"then"|"type"
 Cadena= \"(.[^\"]*)\"
 Logica = True|False
 ParentesisAbierto = \(
 ParentesisCerrado = \)
 Delimitador = {ParentesisAbierto}|{ParentesisCerrado}|\;|\:|\"|\,|\..
 Operador = {Simbolo}|\<|:=|\+|\>|\==|\/=|"and"|"or"|\.
 Constante = {Numero}|{Logica}
 
/* Finaliza expresiones regulares */
 
%%
/* Finaliza la secci�n de declaraciones de JFlex */
 
/* Inicia secci�n de reglas */
 
// Cada regla est� formada por una {expresi�n} espacio {c�digo}

{PalabraReservada} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.PALABRA_RESERVADA);
 this._existenTokens = true;
 return t;
} 
 
{Identificador} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.IDENTIFICADOR);
 this._existenTokens = true;
 return t;
}
 
{Comentario} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.COMENTARIO);
 this._existenTokens = true;
 return t;
} 
 
{Constante} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.CONSTANTE);
 this._existenTokens = true;
 return t;
}

{Cadena} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.CADENA);
 this._existenTokens = true;
 return t;
}

{Delimitador} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.DELIMITADOR);
 this._existenTokens = true;
 return t;
}

{Operador} {
 TokenInfo t = new TokenInfo(yytext(), TokenInfo.OPERADOR);
 this._existenTokens = true;
 return t;
}

{Espacio} {
 
} 
 
{SaltoDeLinea} {
}