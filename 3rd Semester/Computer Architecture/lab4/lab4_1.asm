.486
.model flat, stdcall
option casemap :none ; чувствительность к регистру букв в идентификаторах
include windows.inc
include kernel32.inc
include masm32.inc

includelib kernel32.lib
includelib masm32.lib

.data   
	condition db "Enter the numbers X and Y", 13, 10, 0
	
	
.data?  
	;BUFFER
	buffer db 25 dup (?)
	;
	numberOfCharsWritten dd ?
     	;data
     	
     	x dd ?
     	y dd ?
     	
     	res dd ?  
     	
     	doubleX dd ?
     	max10y dd ?
     	maxDoubleXY dd ?
     	minXY dd ?
     	
	cin dd ?
	cout dd ?
.code
entryPoint:
	push STD_OUTPUT_HANDLE    ; передача параметра в функцию. Описана в windows.inc (нектр число)
	call GetStdHandle           ; вызов системной функции. Запрашивает дескриптор потока вывода	            
	mov cout, EAX        ; сохранение результата функции. Из EAX в cout
	
    	push STD_INPUT_HANDLE
    	call GetStdHandle
   	mov cin, EAX 
;------------------------------------------------------------------------------------------------------
	;Output condition
	push offset condition
	call lstrlen
	
	push NULL                 
    	push NULL
    	push EAX      
    	push offset condition 
    	push cout           
    	call WriteConsole
    	;Input x
     	push NULL
    	push offset numberOfCharsWritten 
    	push 25
    	push offset buffer 
    	push cin 
    	call ReadConsole
    	
    	mov ESI, numberOfCharsWritten
    	mov EBX, offset buffer
    	mov byte ptr [ EBX + ESI - 2 ], 0
    	
    	push offset buffer
    	call atodw
    	mov x, EAX
    	;Input y
     	push NULL
    	push offset numberOfCharsWritten 
    	push 25
    	push offset buffer 
    	push cin 
    	call ReadConsole     
    	
     	mov ESI, numberOfCharsWritten
    	mov EBX, offset buffer
    	mov byte ptr [ EBX + ESI - 2 ], 0
    	
    	push offset buffer
    	call atodw
    	mov y, EAX
;------------------------------------------------------------------------------------------------------
	;x^2
	mov EAX, x
	imul x
	mov doubleX, EAX
	;----------------------------------
	
	  
	;max(y, 10) --------------------------------------------------------------------
	cmp y, 10
	jle TenIsMax ; если десять больше или равно
	mov EAX, y
	mov max10y, EAX
	jmp kek1
	;10 > y
	TenIsMax:
	mov max10y, 10
	 
	;max (x^2, y) --------------------------------------------------------------------
	kek1:
	mov EAX, y
	cmp doubleX, EAX
	jle YIsMax ; если Y больше или равно
	
	mov EAX, doubleX
	mov maxDoubleXY, EAX 
	jmp kek2
	;y > x^2
	YIsMax:
	mov EAX, y
	mov maxDoubleXY, EAX 
	; min(x,y) --------------------------------------------------------------------
	kek2:
	mov EAX, x
	cmp y, EAX
	jle XIsMin ; если Y >= x
	
	mov EAX, y
	mov minXY, EAX 
	jmp Delenie
	
	XIsMin:
        mov EAX, x
        mov minXY, EAX
	; GO TO DIV
        
        
        ; DIV
        Delenie:
	mov EAX, maxDoubleXY
	idiv minXY
        

        mov res, EAX 
        
;------------------------------------------------------------------------------------------------------           
        push offset buffer
        push res
        call dwtoa 
        
        push offset buffer
        call lstrlen
        
        push NULL                 
    	push numberOfCharsWritten 
    	push EAX
    	push offset buffer
    	push cout           
    	call WriteConsole
;------------------------------------------------------------------------------------------------------ 
 	                                                                                                      
    	
;------------------------------------------------------------------------------------------------------    		                                                                                       
	push 0 ; аналогично плюсам return 0                                                            
	call ExitProcess ; Для корректного завершения программы


end entryPoint
