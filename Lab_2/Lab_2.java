import java.util.Scanner;
import java.util.Arrays;




public class Lab_2 {

	static Scanner scanner = new Scanner(System.in); // создаем сканнер для ввода значений с клавиатуры
    public static void main(String[] args) {
		System.out.print("Введите номер задания: ");
		switch(scanner.nextInt()) {
			case 1: // первое задание
				longest_substring();
				break;
			case 2: // второе задание
				merge_sorted_arrays();
				break;
			case 3: // третье задание
				max_subarray_sum();
				break;
			case 4: // четвертое задание
				rotate_array_90_clockwise();
				break;
			case 5: // пятое задание
				target_pair_sum();
				break;
			case 6: // шестое задание
				sum_2d_array();
				break;
			case 7: // седьмое задание
				max_in_rows();
				break;
			case 8: // восьмое задание
				rotate_array_90_counterclockwise();
				break;
			default: // если введено не то значение номера задания
				System.out.print("Ошибка: Неверный номер задания");
		}
		scanner.close();
	}

//------------------------------------------------------------------------------------------------------------------------





    static void longest_substring() { // метод для задачи 1
		System.out.print("Введите строку: "); // вводим строку
		String input_str = scanner.next(); // nextLine не сработает, т.к. nextInt из выбора задания забирает число, но оставляет /n (конец строки), которую и забирает nextLine в качестве ввода
		int current_count_chars = 0; // количество уникальных символов в прочтенной подстроке (которая продолжате считываться)
		char[] chars = new char[input_str.length()]; // создаем массив, в котором будут хранится пройденные символы (длина данного массива = длине введенной строки)
		String longest_substr = ""; // строка, которая будет содержать наш ответ
		String current_substr = ""; // строка, содержащая последовательно прочтенные символы на данный момент без повторений
		for (int i = 0; i < input_str.length(); i++) { // выбираем с какого символа начинаем искать подстроку
			for (int j = i; j < input_str.length(); j++) { // послдеовательно проходимся по символам строки
				char current_char = input_str.charAt(j); //считываем символ из строки
				int matching_chars_count = 0; // число, показывающее количество повторяющихся символов в прочтенной строке
				for (int k = 0; k < chars.length; k++) { // проверяем на совпадающие символы
					if (chars[k] == current_char) { // если повторяющийся символ нашелся, отмечаем что совпадение есть и останавливаем проверку
						matching_chars_count += 1;
						break; 
					}
				}
				if (matching_chars_count != 0) { // если символы начали повторяться
					if (current_substr.length() > longest_substr.length()) { // если длина найденной подстроки больше длины предыдущей самой длинной подстроки
						longest_substr = current_substr;
						current_substr = "";
						chars = new char[input_str.length()];
						current_count_chars = 0;
						break;
					}
					else { // если длина найденной подстроки меньше или равна длине предыдущей самой длинной подстроки
						current_substr = "";
						chars = new char[input_str.length()];
						current_count_chars = 0;
						break;
					}
				}
				else { // если полученный символ является уникальным для данной подстроки
					chars[current_count_chars] = current_char;
					current_substr += current_char;
					current_count_chars += 1;
				}
			}
		}
		System.out.println(("Строка, в которой проводился поиск подстроки: " + input_str));
		System.out.println("Найденная наибольшая подстрока имеет вид: " + longest_substr);
    }





    static void merge_sorted_arrays() { // метод для задачи 2
		System.out.print("Введите количество элементов в первом массиве: "); // заполняем массивы
		int length_1_arr = scanner.nextInt();
		int[] arr1 = new int[length_1_arr];
		System.out.println("Вводите последовательно элементы массива: ");
		for (int i = 0; i < length_1_arr; i++)
			arr1[i] = scanner.nextInt();
		System.out.print("Введите количество элементов во втором массиве: ");
		int length_2_arr = scanner.nextInt();
		int[] arr2 = new int[length_2_arr];
		System.out.println("Вводите последовательно элементы массива: ");
		for (int i = 0; i < length_2_arr; i++)
			arr2[i] = scanner.nextInt();
		int len1 = arr1.length; // длина первого полученного массива
		int len2 = arr2.length; // длина второго полученного массива
		int answer_len = len1 + len2; // длина финального массива
		int[] answer_arr = new int[answer_len]; // создаем финальный массив
		int i = 0; // номер элемента из первого массива
		int j = 0; // номер элемента из второго массива
		while (i+j < answer_len) {
	    	if ( (i < len1) && (j < len2) ) { // если в каждом из массивов еще остались неотсортированные элементы
				int current1 = arr1[i]; // элемент из первого массива
				int current2 = arr2[j]; // элемент из второго массива
				if (current1 < current2) {  // если первый элемент меньше второго
		    		answer_arr[i+j] = current1;
		    		i++;
				}
				else { // если второй элемент меньше первого
		    		answer_arr[i+j] = current2;
		    		j++;
				}
	    	}	
	    	else { // если в одном из массивов закончились неотсортированные элементы
				if (i >= len1) { // если элементы закончились в первом массиве
					answer_arr[i+j] = arr2[j];
					j++;
	    		}
	    		else { // если элементы закончились во втором массиве
					answer_arr[i+j] = arr1[i];
					i++;
	    		}
			}
		}
		System.out.println("Отсортированные массивы имеют вид: " + Arrays.toString(answer_arr));
    }
    




    static void max_subarray_sum() { // метод для задачи 3
		System.out.print("Введите количество элементов в массиве: "); // заполняем массив
		int length_arr = scanner.nextInt();
		int[] arr = new int[length_arr];
		System.out.println("Вводите последовательно элементы массива: ");
		for (int i = 0; i < length_arr; i++)
			arr[i] = scanner.nextInt();
		int max_sum = Integer.MIN_VALUE; // присваиваем самое минимальное значение, чтобы ЛЮБАЯ сумма подмассива была больше нее
		int current_sum = 0; // сумма уже прочтенных элементов
		for (int i : arr) {
	    	current_sum += i;
	    	if (current_sum >= max_sum) // если прибавленное число  - положительное
				max_sum = current_sum;
	    	if (current_sum < max_sum) // если прибавленное число - отрицательное
				current_sum -= i;
		}
		System.out.println("Максимальная сумма подмассива равна: " + max_sum);
    }





    static void rotate_array_90_clockwise() { // метод для задачи 4 (Представляем, что изначально каждый столбец это отдельный массив, а строки это элементы массива)
		System.out.print("Введите количество подмассивов двумерно массива: "); // заполняем двумерный массив
		int length_1 = scanner.nextInt();
		int[][] arr = new int[length_1][1];
		for (int i = 0; i < length_1; i++) {
			System.out.print("Введите количество элементов в этом подмассиве: ");
			int length_2 = scanner.nextInt();
			arr[i] = new int[length_2];
			System.out.println("Введите последовательно элементы подмассива: ");
			for (int j = 0; j < length_2; j++)
				arr[i][j] = scanner.nextInt();
		}

		// ВАЖНО Т.к. выполнение задания должно показать понимание работы с массивами (в данной лабораторной), то я не рассматривал те случаи, где двумерные массивы НЕ МОГУТ БЫТЬ представлены
		// в  виде прямоугольных матриц, в которых каждый столбец это отдельный массив. Поэтому такие двумерные массивы, как например [[1,2,3,4][8,9][5,6,7]] я не рассматривал из-за их
		// большой сложности реализации поворота по и против (большей сложности, чем подразумевалось, на мой взгляд, в лабораторной). К примеру, при повороте этого массива мы должны получить
		// следующие результаты:
		//Исходный двумерный массив: | 1 | 8 | 5 |. После поворота на 90 по часовой: | 4 | 3 | 2 | 1 |. После поворота на 90 против часовой: | 5 | 6 | 7 | 4 |.
		//                           | 2 | 9 | 6 |                                   |   | 7 | 9 | 8 |                                       | 8 | 9 | 3 |   |
		//                           | 3 |   | 7 |                                   |   |   | 6 | 5 |                                       | 1 | 2 |   |   |
		//                           | 4 |   |   |
		// Поэтому для заданий 4 и 8 методы работают только для "прямоугольных массивов".

		int column_count = arr[0].length; //количество столбцов в повернутом массиве
		int lines_count = arr.length; // количество строк в повернутом массиве
		int[][] rotated_arr = new int[column_count][lines_count]; // создаем пустой повернутый массив
		for (int i = 0; i < column_count; i++)
			for (int j = 0; j < lines_count; j++)
				rotated_arr[i][j] = arr[j][column_count - i - 1]; // заполняем значениями
		System.out.println("Повернутый на 90 градусов по часовой стрелке массив принял вид: " + Arrays.deepToString(rotated_arr));
    }





    static void target_pair_sum() { // метод для задачи 5
		System.out.print("Введите искомую сумму чисел: ");
		int target = scanner.nextInt();
		System.out.print("Введите количество элементов в массиве: ");
		int length = scanner.nextInt();
		int[] arr = new int[length];
		System.out.println("Введите последовательно элементы массива: ");
		for (int i = 0; i < length; i++)
			arr[i] = scanner.nextInt();
		int completed = 0; // показатель того, что такая пара чисел есть
		for (int i = 0; i < arr.length-1; i++) // берем индекс первого элемента
	    	for (int j = i+1; j < arr.length; j++) // берем индекс второго элемента
				if (arr[i] + arr[j] == target) { // если такие элеенты найдены
		    		int[] pair = { arr[i], arr[j] }; // создаем массив, который хранит два элемента, сумма которых равна заданному числу
					System.out.println("Пара элементов, дающая нужную сумму:" + Arrays.toString(pair));
					completed = 1;
				}
		if (completed == 0)
			System.out.println("null"); // если такая пара не была найдена
    }





    static void sum_2d_array() { // метод для задачи 6
		System.out.print("Введите количество подмассивов двумерно массива: "); // заполняем двумерный массив
		int length_1 = scanner.nextInt();
		int[][] arr = new int[length_1][1];
		for (int i = 0; i < length_1; i++) {
			System.out.print("Введите количество элементов в этом подмассиве: ");
			int length_2 = scanner.nextInt();
			arr[i] = new int[length_2];
			System.out.println("Введите последовательно элементы подмассива: ");
			for (int j = 0; j < length_2; j++)
				arr[i][j] = scanner.nextInt();
		}
		int answer = 0;
		for (int i = 0; i < arr.length; i++) // индекс столбца
	    	for (int j = 0; j < arr[i].length; j++) // индекс строки
				answer += arr[i][j]; // прибавляем
		System.out.println("Сумма всех элементов двумерного массива: " + answer);
    }





    static void max_in_rows() { // метод для задачи 7
		System.out.print("Введите количество подмассивов двумерно массива: "); // заполняем двумерный массив
		int length_1 = scanner.nextInt();
		int[][] arr = new int[length_1][1];
		for (int i = 0; i < length_1; i++) {
			System.out.print("Введите количество элементов в этом подмассиве: ");
			int length_2 = scanner.nextInt();
			arr[i] = new int[length_2];
			System.out.println("Введите последовательно элементы подмассива: ");
			for (int j = 0; j < length_2; j++)
				arr[i][j] = scanner.nextInt();
		}
		int[] row_max = new int[arr.length]; // массив, содержащий максимальные значения в каждом из массивов
		for (int i = 0; i < arr.length; i++) {
	    	int cur_max = Integer.MIN_VALUE; // присваиваем минимально возможное, чтобы любое число было больше него
	    	for (int elem : arr[i]) 
				if (elem > cur_max) // если прочтенный элемент больше "максимального ранее", тогда меняем
		    		cur_max = elem;
			row_max[i] = cur_max;
		}
		System.out.println("Массив из максимальных элементов каждого из подмассивов: " + Arrays.toString(row_max));
    }





    static void rotate_array_90_counterclockwise() { // метод для задачи 8
		System.out.print("Введите количество подмассивов двумерно массива: "); // заполняем двумерный массив
		int length_1 = scanner.nextInt();
		int[][] arr = new int[length_1][1];
		for (int i = 0; i < length_1; i++) {
			System.out.print("Введите количество элементов в этом подмассиве: ");
			int length_2 = scanner.nextInt();
			arr[i] = new int[length_2];
			System.out.println("Введите последовательно элементы подмассива: ");
			for (int j = 0; j < length_2; j++)
				arr[i][j] = scanner.nextInt();
		}
		int column_count = arr[0].length; //количество столбцов в повернутом массиве
		int lines_count = arr.length; // количество строк в повернутом массиве
		int[][] rotated_arr = new int[column_count][lines_count]; // создаем пустой повернутый массив
		for (int i = 0; i < column_count; i++)
			for (int j = 0; j < lines_count; j++)
				rotated_arr[i][j] = arr[lines_count - j - 1][i]; // заполняем значениями
		System.out.println("Повернутый на 90 градусов против часовой стрелки массив принял вид: " + Arrays.deepToString(rotated_arr));
	}
}