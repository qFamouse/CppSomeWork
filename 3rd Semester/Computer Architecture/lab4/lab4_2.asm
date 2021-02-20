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
	
	kek dd 0
	
.data?  
	;BUFFER
	buffer db 25 dup (?)
	;
	numberOfCharsWritten dd ?
     	;data
     	
     	x dd ?
     	y dd ?
     	
     	res dd ?  
     	
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
	mov EAX, 0
	NachaloCikla:
	add EAX, x
	inc kek
	; proverka
	mov EDX, 0
	mov EDX, y
	cmp kek, EDX
	jne NachaloCikla
        
        
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
