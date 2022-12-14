# Example_Projects.Business_Process_Automation
Автоматизация бизнес процесса, связанного с переводами различных сумм между участниками системы.

## Краткое описание
Программа представляет собой консольное приложение с меню, поддерживает два типа запуска - **dev** и **prod**. Для каждого есть соответствующие команды в make. Dev отличается более широкими возможностями (удаление транзакций, проверка правильности транзакций).

## Про файловую структуру
- Program - главный класс;
- Menu - непосредственное отображение работы программы и связь с TransactionsService;
- TransactionsService - логика работы основных функций программы (исключая формирование вывода, это делает Menu);
- TransactionsList, UsersList - интерфейсы для реализации логики работы TransactionsLinkedList (подобие LinkedList) и UsersArrayList (подобие ArrayList);
- IllegalTransactionException, TransactionNotFoundException, UserNotFoundException - свои исключения для обработки некоторых ситуаций;
- Transaction - сущность транзакции;
- User - сущность пользователя;
- UserIdsGenerator - синглтон для генерации уникальных порядковых id у пользователей.

>*Примечание:
>в процессе, для лучшего понимания их работы, проектировались собственные реализации частных случаев нескольких коллекций (LinkedList, ArrayList).*

### Список функций:
* Добавить пользователя
* Узнать баланс пользователя
* Сделать перевод
* Посмотреть историю переводов и поступлений
* Удалить транзакцию (только для **dev**)
* Проверить транзакции на ошибки (они хранятся попарно, и при ручно удалении одной транзакции из пары, может остаться транзакция без пары, на это и нацелена проверка, только для **dev**)
* Завершить работу программы

### Примеры работы
*В режиме prod*

![prod_example](/misc/prod_example.png "Пример работы в prod режиме")

*В режиме dev*

![example_dev_1](/misc/example_dev_1.png "Пример работы в dev режиме, первая часть")
![example_dev_2](/misc/example_dev_2.png "Пример работы в dev режиме, вторая часть")
