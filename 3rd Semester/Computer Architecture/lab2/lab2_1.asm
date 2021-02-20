.486
.model flat, stdcall
option casemap :none ; ���������������� � �������� ���� � ���������������
include windows.inc
include kernel32.inc
includelib kernel32.lib

.data         
	buffer db 52 dup ('+')
.data?
       	
       	
	cin dd ?
	cout dd ?
	numberOfChars dd ?
.code
entryPoint:
	push STD_OUTPUT_HANDLE    ; �������� ��������� � �������. ������� � windows.inc (����� �����)
	call GetStdHandle           ; ����� ��������� �������. ����������� ���������� ������ ������	            
	mov cout, EAX        ; ���������� ���������� �������. �� EAX � cout
	
   	push STD_INPUT_HANDLE
	call GetStdHandle
	mov cin, EAX 
;------------------------------------------------------------------------------------------------------	
	; ����
  	push NULL
  	push offset numberOfChars
  	push 50
  	push offset buffer + 1
  	push cin 
  	call ReadConsole
  	;��������������
       	mov ESI, numberOfChars ; ���-�� ���������� ��������
    	mov EBX, offset buffer ; ����� ������ �������
     	mov byte ptr [ EBX ], '{'
 	mov byte ptr [ EBX + ESI - 1 ], '}'
	;�����
	push NULL                 ; 5-�� �������� �������, ��������� ���������, �������� ��������������, ����� �� ������������, �������� ������� � ����� �������
	push offset numberOfChars ; 4-�� �������� �������, ����� ���������� ���� �-� �������� ���-�� ��������, ������� �� ������� �������
	push ESI      ; 3-�� �������� �������, ������������� ���������. ������� �������� ��������(���� ���������)
	push offset buffer     ; 2-�� �������� �������, ����� ������� ��������. ����� ��� ������ �����
	push cout              	  ; 1-�� �������� �������, ���������� ���������� �������. ���� �� �������
	call WriteConsole
	
	push 0 ; ���������� ������ return 0
	call ExitProcess ; ��� ����������� ���������� ���������

end entryPoint
