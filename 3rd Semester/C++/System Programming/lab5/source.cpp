#include <windows.h>
#include <iostream>
#include <tchar.h>

#define Task 2

using namespace std;

int main()
{
#if Task == 1 // Task 1 // // // // // // // // // // // // // // // // // // 
	TCHAR buffer[MAX_PATH];
	if (GetCurrentDirectory(sizeof(buffer), buffer) == 0) {
		cout << "Error get current directory: #" << GetLastError() << endl;
	}
	wcout << "Current directory: " << buffer << endl;
	system("pause");
	SetCurrentDirectory(L"C:\\");
	if (GetCurrentDirectory(sizeof(buffer), buffer) == 0) {
		cout << "Error get current directory: #" << GetLastError() << endl;
	}
	wcout << "Current directory: " << buffer << endl;
#elif Task == 2 // Task 2 // // // // // // // // // // // // // // // // // // 
	//	Subwork #1 Create Directory
	LPCWSTR TempDirectory = L"E:\\Temp";
	if (!CreateDirectory(TempDirectory, NULL)) {
		cout << "Error create new directory: #" << GetLastError() << endl;
		system("pause");
	}
	//	Subwork #2 Copy File
	//LPCWSTR ExistingFile = L"Z:\\Новый\\2kurs\\SysProg\\test.info";
	LPCWSTR ExistingFile = L"E:\\kek\\kek.info";
	LPCWSTR NewFile = L"E:\\Temp\\test.info";
	if (CopyFile(ExistingFile, NewFile, FALSE) == FALSE) {
		cout << "Error copy file: #" << GetLastError() << endl;
		system("pause");
	}
	//	Subwork #3 Open File 
	HANDLE hTestInfo;
	if ((hTestInfo = CreateFile(NewFile, GENERIC_READ, 0, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_READONLY, NULL)) == INVALID_HANDLE_VALUE) {
		cout << "Error create file test.info: #" << GetLastError() << endl;
		system("pause");
	}
	// Subwork #4 Read File
	SetFilePointer(hTestInfo, 100, NULL, FILE_BEGIN);
	// 271014
	const INT DataLenght = 126;
	TCHAR buffer[DataLenght];
	DWORD DataRead;
	if (ReadFile(hTestInfo, buffer, DataLenght, &DataRead, NULL) == FALSE) {
		cout << "Error read file: #" << GetLastError() << endl;
		system("pause");
	}

	cout << "We read: " << DataRead << endl;
	// Subwork #5 Writting to answer.txt
	HANDLE hAnswer;
	LPCWSTR AnswerDirectory = L"E:\\Temp\\answer.txt";
	if ((hAnswer = CreateFile(AnswerDirectory, GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL)) == INVALID_HANDLE_VALUE) {
		cout << "Error create file answer.txt: #" << GetLastError() << endl;
		system("pause");
	}
	DWORD DataWrite;
	if (WriteFile(hAnswer, buffer, DataRead, &DataWrite, NULL) == FALSE) {
		cout << "Error write file: #" << GetLastError() << endl;
		system("pause");
	}
	cout << "We write: " << DataWrite << endl;
	CloseHandle(hTestInfo);
	CloseHandle(hAnswer);
#endif
	system("pause");
	return 0;
}