#include <windows.h>
#include <iostream>
#include <tchar.h>

using namespace std;

int main() {
	// String in UNICODE
	WCHAR UniStr[] = L"Hello World";
	wcout << UniStr << endl;

	// Transformation encodings
	WCHAR NoConvertingStr[] = L"Hi World";
	CHAR ConvertingStr[sizeof(NoConvertingStr)];

	WideCharToMultiByte(CP_UTF8, 0, NoConvertingStr, -1, ConvertingStr, sizeof(ConvertingStr), 0, 0);
	cout << ConvertingStr << endl;


	// Does not depend on encoding
	TCHAR NoDependCodi[] = _T("Hello");

	_tprintf(NoDependCodi);
	cout << endl;

	system("pause");
	return 0;
}