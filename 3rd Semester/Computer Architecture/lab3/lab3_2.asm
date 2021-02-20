.486
.model flat, stdcall
option casemap :none ; чувствительность к регистру букв в идентификаторах
include windows.inc
include kernel32.inc
include masm32.inc

includelib kernel32.lib
includelib masm32.lib

.data   
	condition db "Enter the numbers A and B", 13, 10, 0
	
	two dd 2, 0
.data?  
	;BUFFER
	buffer db 25 dup (?)
	;
	numberOfCharsWritten dd ?
     	;data
     	a dd ?
     	b dd ?
     	res dd ?
     	
     	r dd ?
     	l dd ?
     	
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
    	;Input firstnum
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
    	mov a, EAX
    	;Input secondnum	
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
    	mov b, EAX 
;------------------------------------------------------------------------------------------------------    
        ;multiply a*3
        mov EAX, a
        imul EAX, 3
        mov l, EAX

	mov EAX, a
        add EAX, b
        idiv two
        mov r, EAX
        
        mov EAX, l
        sub EAX, r
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
	push 0 ; аналогично плюсам return 0
	call ExitProcess ; Для корректного завершения программы

end entryPoint
