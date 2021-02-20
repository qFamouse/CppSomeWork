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

.data?
        BodyWeight struct
        	day dd ?
        	month dd ?
        	year dd ?
        	result dq ?
        BodyWeight ends
                                
        buffer db 1000 dup(?)
	                            
	clear dq ?
        
        
	numberOfCharsWritten dd ?
       	cin dd ?
	cout dd ?    
.data         
	template db "%.2d.%.2d.%.d - %3.10s kg", 10, 13, 0      
	kek dd 10, 13, 0
	arrayBW BodyWeight <3, 4, 2002, 52.0>, <1, 3, 2001, 51.0>, <7, 4, 2002, 54.0>, <10, 6, 2002, 60.0>, <24, 7, 2002, 58.0>, <28, 8, 2002, 63.0> 
	count dd 6                     
	
	strDqBuf db 100 dup(0)
	n24 dd 24  
	n16 dd 16 
	n3 dd 3
	n4 dd 4
	n12 dd 12  
	n20 dd 20
.code     
;\\ \\ \\ \\ \\ \\ \\ \\ COMPARE BY DATA \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\
compareByData:
	mov EBX , [ ESP +8]; EBX = *second
	mov EDX , [ ESP +4]; EDX = *first   
	mov ESI,0; ESI = i 
	mov EAX, 0
biginCompareLoop: 
	cmp ESI, n3
	je  endCompareLoop; if(ESI == 4)то завершаем цикл, это значит что первые 4 поля струткуры равны 
	cmp EAX, 0
	jne endCompareLoop  ; if(ESI !=0) если ESI продолжает быть равным 0 то проверяемые поля равны и наужно проверять остальные
        mov EAX , dword ptr [ EDX ]
        sub EAX , dword ptr [ EBX ]; first[one..four] - second[ane..four]
        add EBX, n4
        add EDX, n4      
        
        inc ESI
        jmp biginCompareLoop 
endCompareLoop:  
ret 8



         
;\\ \\ \\ \\ \\ \\ \\ \\ COMPARE BY WEIGHT \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\
CompareByWeight: ; int compareByWeight(BodyWeight *first, BodyWeight *second)
	; -1 - первое больше | 0 - равны | 1 - второе больше
	mov EDX, [ ESP + 4 ] ; EDX = firts
	add EDX, n12
	fld qword ptr [ EDX ] ; stack -> EDX(first)
	mov EDX , [ ESP +8 ]; EDX = *secondFRTS  
	add EDX, n12
	fcom qword ptr [ EDX ] ; compare first and second
	fstp clear         
	fstsw AX
	sahf
	jb firstBelow
	ja firstAbove
	mov EAX,0
 	jmp CompareByWeightEnd
 	
firstBelow: ; if (first < second)
	mov EAX, -1
	jmp CompareByWeightEnd
firstAbove: ; if (first > second)
	mov EAX, 1               
	jmp CompareByWeightEnd
CompareByWeightEnd:
ret 8
;\\ \\ \\ \\ \\ \\ \\ \\ CUSTOM SORT \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  
selectionSort: ; selectionSort(LRTS* array,int N, func)
        
	mov EDI,   [ ESP + 8 ]; EDI = N
      	dec EDI; EDI = N-1   
      	push EDI
      	add ESP,4
      	mov EDI,0
outerLoop:
	cmp EDI , dword ptr [ ESP -4] ; сравнение ECX c N -1 
	jnb endLoopSort    ;если i>=N-1 цикл заканчивается 
	
	;начала внутреннего цикла 

	
       	mov ECX,EDI ; ECX <=> j ECX = i
       	inc ECX
innerLoop:
	cmp ECX , [ ESP + 8 ]
	jae endInnerLoop;  j>=N внутренний цикл заканчивается 
	
        
        mov EAX, ECX   ; EAX = ECX
        mul n20 ; EAX *= sizeofLRTS
       	add EAX,  [ ESP + 4 ]; EAX = EAX + *array
        push  EAX  ;передача  *array[j] в функцию 
        
        mov EAX,EDI ; EAX = i
        mul n20 ;EAX = i*sizeofLRTS 
        add EAX ,  [ ESP + 8 ] ;EAX += *array
        push EAX; передача  *array[i]  в функцию        
        call dword ptr [ ESP + 20 ]
        cmp EAX ,  0
        jge skipSwap
        ;if(array[i]<array[j])
        mov EAX, ECX   ; EAX = ECX
        mul n20 ; EAX *= sizeofLRTS
       	add EAX,  [ ESP + 4 ]; EAX = EAX + *array
        push  EAX  ;передача  *array[j] в функцию 
        
        mov EAX,EDI ; EAX = i
        mul n20 ;EAX = i*sizeofLRTS 
        add EAX ,  [ ESP + 8 ] ;EAX += *array
        push EAX; передача  *array[i]  в функцию 
        call  swap
        
skipSwap:        
        inc ECX
        jmp innerLoop
endInnerLoop: ;конец внутреннего цикла 

	inc EDI
	jmp outerLoop
	
endLoopSort:   
ret 12
;\\ \\ \\ \\ \\ \\ \\ \\ SWAP \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\          
swap:
	mov EBX,0;EBX<=>i EBX - индекс
swapLoop: 
	cmp EBX, n3
	je endSwap
        ;temp = first.[any..three]
        mov EAX, EBX
        mul n4 ; EAX = 4*i - отступаем необходимое кол-во байтов
        add EAX, dword ptr [ ESP + 4 ] 
        mov ESI, EAX ; ESI = *first.[any..three]        
        mov EDX, dword ptr [ EAX ] ; EDX = first.[any..three]
        mov EBP, EDX; стек: ... first.[any..three](temp) ,EBP,позиция возврата, *first, *second 
	; first.[any..three] =  second.[any..three]         
	mov EAX , EBX                                     
	mul n4;	EAX = 4*i          
	add EAX , dword ptr [ ESP +8 ];EAX = *second.[any..four]       
	mov EDX,dword ptr [ EAX ]; EDX = second.[any..four] 
	mov dword ptr [ ESI ], EDX; first.[any..four] =  second.[any..four]      
	
	; second.[any..four] = temp
	mov EAX , EBX ; EAX = i
	mul n4 ;	EAX = 4*i 
	add EAX , dword ptr [ ESP + 8];EAX = *second.[any..four]  
	mov ESI , EBP ; ESI = temp
	mov dword ptr [ EAX ], ESI
	inc EBX
	jmp swapLoop
endSwap:
	;обмен  freqReq
	mov ESI, dword ptr [ ESP + 4] ; ESI = *first
       	add ESI, n12
	fld qword ptr [ ESI  ]; стек: first.freqReq        
	mov EDX, dword ptr [ ESP + 8]; EDX = *second 
	add EDX, n12
	fld qword ptr [ EDX ]; стек: second.freqReq, first.freqReq
       	fstp qword ptr [ ESI ]; first.freqReq = second.freqReq    стек: first.freqReq  
	fstp qword ptr [ EDX ];стек: пуст 
ret 8

print:
	mov ESI, [ ESP + 4 ] ; Array
	mov EDI, 0           ; 
beginPrint:
	cmp EDI, [ ESP + 8 ]
	jae endPrint
	
	push offset strDqBuf
	push dword ptr [ ESI + 16 ]
	push dword ptr [ ESI + 12 ]
	call FloatToStr
	
	push offset strDqBuf
	push dword ptr [ ESI + 8 ]
	push dword ptr [ ESI + 4 ]
        push dword ptr [ ESI ]
	push offset template
        push offset buffer
        call wsprintf     
        
        add ESP, 24
        
        push offset buffer
        call lstrlen
        
       	push NULL
	push offset numberOfCharsWritten 
	push EAX
	push offset buffer
	push cout
	call WriteConsole  
                     
        add ESI, 20
        inc EDI
        jmp beginPrint  
endPrint:
ret 8 
             






entryPoint:
; Дескрипторы ввода/вывода
	push STD_INPUT_HANDLE
	call GetStdHandle
	mov cin, EAX
	push STD_OUTPUT_HANDLE
	call GetStdHandle
	mov cout, EAX
;        

push count
push offset arrayBW
call print  
                

                         	push NULL
     	push offset numberOfCharsWritten 
    	push 2
   	push offset kek
  	push cout
 	call WriteConsole 

                 
                 
push CompareByWeight
push count
push offset arrayBW
call selectionSort
                  
push count
push offset arrayBW
call print  
                         
                   
                         	push NULL
     	push offset numberOfCharsWritten 
    	push 2
   	push offset kek
  	push cout
 	call WriteConsole 
                   
push compareByData
push count
push offset arrayBW
call selectionSort
                  
push count
push offset arrayBW
call print  

;===========================   


;Выход из программы	
	push 20000
	call Sleep

	push 0
	call ExitProcess
END entryPoint