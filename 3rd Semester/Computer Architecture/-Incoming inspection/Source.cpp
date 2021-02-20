#include <Windows.h>
#include <iostream>
#include <fstream>

using namespace std;

class FeHelper {
	int a, b;
	char operation;

public:
	FeHelper(int a, int b, char operation) {
		this->a = a;
		this->b = b;
		this->operation = operation;
	}

	int GetResult() {
		switch (operation)
		{
		case '+': return a + b;
		case '-': return a - b;
		case '*': return a * b;
		case '/': return a / b;
		default:
			cout << "Обнаружен неправильный ввод данных! Правильный ввод: A + B" << endl;
			exit(0);
		}
	}

	void ShowExpression() {
		printf("%i %c %i", a, operation, b);
	}
};


int main() {
	// Русский язык
	setlocale(LC_ALL, "Russian");
	// Подключаю файл и делаю некоторые проверки
	ifstream file("file.txt");
	if (!file.is_open()) {
		printf("Проблема с открытием файла!\n");
		system("pause");
		return 0;
	}

	file.seekg(0, ios::end);
	int file_size;
	file_size = file.tellg();
	if (file_size == 0) {
		cout << "Импортируемый файл пуст!" << endl;
		system("pause");
		return 0;
	}
	file.seekg(0);

	char* a = new char[128];
	char* operation = new char[128];
	char* b = new char[128];
	while (!file.eof()) {
		file.getline(a, 128, ' ');
		file.getline(operation, 128, ' ');
		file.getline(b, 128, '\n');

		FeHelper Decision(atoi(a), atoi(b), *operation);
		Decision.ShowExpression();
		cout << " = " << Decision.GetResult() << endl;
	}

	
	file.close();
	delete a, b, operation;
	system("pause");
	return 0;
}