#include <Windows.h>
#include <iostream>
#include <time.h>
using namespace std;

class Stack {
private:
	int* MyStack;
	int size;
	int top;
public:
	Stack(int maxSize = 10) { // Конструктор
		MyStack = new int[maxSize];
		top = 0;
		size = maxSize;
	}

	void Push(const int &count) { // Добавить в стек
		if (top < size) {
			MyStack[top] = count;
			top++;
		}
		else {
			printf("\nСтек полный. Заполнено %d/%d элементов!", top, size);
		}
	}

	void Pop() { // Удалить из стека
		if (top > 0) {
			MyStack[top--];
		}
		else {
			printf("\nСтек пуст.");
		}
	}

	int GetCurrentElement(int needcount) { // Показать элемент стека
		if (top > 0) {
			return MyStack[needcount];
		}
		else {
			printf("\nСтек пуст.");
		}
	}

	bool StackCheck() { // Стек пустой?
		return top > 0;
	}

	~Stack() { // Деструктор
		delete [] MyStack;
	}
};

int random(int min, int max) {
	int random = min + rand() % (max - min);
	return random;
}

int main() {
	// Чиним рандом
	srand(time(NULL));
	// Russian 
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	setlocale(LC_ALL, "Russian");
	//
	int size;
	int parity = 0; // четный
	int oddness = 0; // нечетный

	cout << "Введите размерность первого стека: ";
	cin >> size;
	Stack FisrtStack(size);
	for (int x = 0; x < size; x++) {
		int element = random(1, 10);
		if (element % 2 == 0) {
			parity++;
		}
		else {
			oddness++;
		}
		FisrtStack.Push(element);
	}
	printf("Стек состоит из элементов:\n");
	for (int i = 0; i < size; i++) {
		printf("%d\n", FisrtStack.GetCurrentElement(i));
	}
	printf("-----------------------\n");
	Stack NewFirstStack(size);
	Stack NewSecontStack(size);

	for (int i = 0; i < size; i++) {
		if (FisrtStack.GetCurrentElement(i) % 2 == 0) {
			NewFirstStack.Push(FisrtStack.GetCurrentElement(i));
			FisrtStack.Pop();
		}
		else {
			NewSecontStack.Push(FisrtStack.GetCurrentElement(i));
			FisrtStack.Pop();
		}
	}

	printf("Стек для четных элементов:\n");
	for (int i = 0; i < parity; i++) {
		printf("%d\n", NewFirstStack.GetCurrentElement(i));
	}
	if (!NewFirstStack.StackCheck()) {
		printf("Элементов нет.\n");
	}
	printf("-----------------------\n");

	printf("Стек для нечетных элементов:\n");
	for (int i = 0; i < oddness; i++) {
		printf("%d\n", NewSecontStack.GetCurrentElement(i));
	}
	if (!NewSecontStack.StackCheck()) {
		printf("Элементов нет.\n");
	}
	printf("-----------------------\n");

	system("pause");
	return 0;
}