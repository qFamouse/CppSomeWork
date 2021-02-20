.486
.model flat, stdcall
option casemap :none
include windows.inc
include kernel32.inc
include masm32.inc
include user32.inc
includelib kernel32.lib
includelib masm32.lib
includelib user32.lib

.data
	condition db "y = {sqrt((-%s * x) + %s), x < 1", 13, 10, "{%s * ln(x), x >= 1", 13, 10, 0 
	
	OutputMSG db "| %4.6s | %4.6s |", 13, 10, 0
	                       
	inputMSG db "Input index of Fibonacci sequence", 13, 10, 0       
	
	i dq 0.0, 0
        j dq 1.0, 0

.data?    
	StrIndex dd ?                  
	NumberIndex dd ?
                          
	StrResult dq ?
        NumberResult dq ? 
	
        k dq ?
	kint dq ?   
	
       	dqbuf2 dq ?
       	     
       	RStr db ?
       	
       	numberOfCharsWritten dd ?
       	cin dd ?
	cout dd ?
.code
     
Fibonacci:
mov ESI, 0
     
FLD j
FLD i


FibonacciStart:
cmp ESI, NumberIndex
jnl FibonacciFinish
FADD ST, ST(1)

inc ESI
jmp FibonacciStart


FibonacciFinish:
FSTP NumberResult
ret






entryPoint:
; Дескрипторы ввода-вывода
	push STD_INPUT_HANDLE
	call GetStdHandle
	mov cin, EAX
	push STD_OUTPUT_HANDLE
	call GetStdHandle
	mov cout, EAX
;Просим ввести число
	push offset inputMSG 
	call lstrlen
	
	push NULL
	push offset numberOfCharsWritten
	push EAX
	push offset inputMSG
	push cout
	call WriteConsole
;ВВодим число
	push NULL
	push offset numberOfCharsWritten
	push 1000
       	push offset StrIndex
        push cin
      	call ReadConsole    
      	
	push offset StrIndex
    	call atodw        
    	mov NumberIndex, EAX
;Основной код
;call Fibonacci    

; ???????? ???????? ???? 
;------------------------------------------------------------------------------------------------------     
    ; 	push offset StrResult 
     ; 	push dword ptr NumberResult + 4
      ;	push dword ptr NumberResult
      ;	call FloatToStr
                
        push offset StrIndex 
        push NumberIndex
        call dwtoa
        
        push offset StrIndex 
        call lstrlen
        
        push NULL                 
    	push numberOfCharsWritten 
    	push EAX
    	push offset StrIndex 
    	push cout           
    	call WriteConsole

;Выход из программы	
	push 0
	call ExitProcess
END entryPoint
