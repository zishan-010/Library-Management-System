import java.util.Scanner

data class Book(
	val id: Int,
	val title: String,
	val author: String,
	var isIssued: Boolean = false
)

fun main() {
	val scanner = Scanner(System.`in`)
	val books = mutableListOf<Book>()

	var choice: Int

	do {
		println("\n===== Library Management System =====")
		println("1. Add Book")
		println("2. View All Books")
		println("3. Issue Book")
		println("4. Return Book")
		println("5. Search Book")
		println("6. Exit")
		print("Enter your choice: ")

		choice = scanner.nextInt()
		scanner.nextLine()

		when (choice) {
			1 -> addBook(scanner, books)
			2 -> viewBooks(books)
			3 -> issueBook(scanner, books)
			4 -> returnBook(scanner, books)
			5 -> searchBook(scanner, books)
			6 -> println("Exiting... Thank you!")
			else -> println("Invalid choice. Please try again.")
		}
	} while (choice != 6)
}

fun addBook(scanner: Scanner, books: MutableList<Book>) {
	print("Enter Book ID: ")
	val id = scanner.nextInt()
	scanner.nextLine()

	print("Enter Book Title: ")
	val title = scanner.nextLine()

	print("Enter Author Name: ")
	val author = scanner.nextLine()

	val book = Book(id, title, author)
	books.add(book)

	println("Book added successfully.")
}

fun viewBooks(books: MutableList<Book>) {
	if (books.isEmpty()) {
		println("No books available in the library.")
		return
	}

	println("\n----- Book List -----")
	for (book in books) {
		val status = if (book.isIssued) "Issued" else "Available"
		println("ID: ${book.id}, Title: ${book.title}, Author: ${book.author}, Status: $status")
	}
}

fun issueBook(scanner: Scanner, books: MutableList<Book>) {
	print("Enter Book ID to issue: ")
	val id = scanner.nextInt()

	var found = false
	
	for (book in books) {
		if (book.id == id) {
			found = true
			if (!book.isIssued) {
				book.isIssued = true
				println("Book issued successfully.")
			} else {
				println("Book is already issued.")
			}
			break
		}
	}

	if (!found) {
		println("Book not found.")
	}
}

fun returnBook(scanner: Scanner, books: MutableList<Book>) {
	print("Enter Book ID to return: ")
	val id = scanner.nextInt()

	var found = false

	for (book in books) {
		if (book.id == id) {
			found = true
			if (book.isIssued) {
				book.isIssued = false
				println("Book returned successfully.")
			} else {
				println("Book was not issued.")
			}
			break
		}
	}

	if (!found) {
		println("Book not found.")
	}
}

fun searchBook(scanner: Scanner, books: MutableList<Book>) {
	print("Enter book title to search: ")
	val searchTitle = scanner.nextLine()

	var found = false

	for (book in books) {
		if (book.title.equals(searchTitle, ignoreCase = true)) {
			val status = if (book.isIssued) "Issued" else "Available"
			println("Book Found -> ID: ${book.id}, Title: ${book.title}, Author: ${book.author}, Status: $status")
			found = true
		}
	}
	
	if (!found) {
		println("Book not found.")
	}
}