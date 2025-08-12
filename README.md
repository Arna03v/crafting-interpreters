# crafting-interpreters

This repository contains the code I have written while reading the book "Crafting Interpreters". 

First half of the book is implementing lox using java, the second is doing the same but using C

### Lox

* Is dynamically typed.
    * Variables can store values of any type, and a single variable can even store values of different types at different times.
    * Type errors are detected at runtime.
* Has automatic memory management.
    * Uses a tracing garbage collector (GC).

---

### Lox Data Types

1.  **Booleans**
    * A dedicated boolean type.
    * `true`
    * `false`
2.  **Numbers**
    * Only one kind: double-precision floating-point.
    * Can represent both integers (`1234`) and decimals (`12.34`).
3.  **Strings**
    * Enclosed in double quotes: `"I am a string"`.
4.  **`nil`**
    * Represents "no value", similar to `null` in other languages.
    * The name `nil` is used to help distinguish it from the `null` keyword in the implementation languages (Java/C).

---

### Expressions

1.  **Arithmetic**
    * **Binary operators**: `+`, `-`, `*`, `/`.
        * These work on numbers.
        * The `+` operator can also be used to concatenate two strings.
    * **Unary operator**: `-` can be used as a prefix to negate a number.
2.  **Comparison and Equality**
    * **Comparison operators**: `<`, `<=`, `>`, `>=`.
        * These work only on numbers.
    * **Equality operators**: `==`, `!=`.
        * These work on values of any type.
        * Values of different types are never equal (e.g., `123 == "123"` is `false`).
3.  **Logical Operators**
    * **`!`**: Prefix operator for logical NOT.
    * **`and`**: Logical AND. Short-circuits (doesn't evaluate the right side if the left is false).
    * **`or`**: Logical OR. Short-circuits (doesn't evaluate the right side if the left is true).
4.  **Grouping**
    * Parentheses `()` can be used to explicitly control the order of operations.

---

### Statements

* Statements produce an effect rather than a value.
1.  **`print` Statement**
    * Evaluates an expression and displays the result. `print "Hello";`
2.  **Expression Statement**
    * An expression followed by a semicolon `;`. `"some expression";`
3.  **Block Statement**
    * A series of statements grouped within curly braces `{}`.
    * Introduces a new scope.

---

### Variables

* Declared using a `var` statement: `var name = "value";`
* If no initializer is provided, the variable defaults to `nil`.
* After declaration, a variable can be accessed and reassigned to.

---

### Control Flow

1.  **`if` Statement**
    * Executes code based on a condition, with an optional `else` branch.
2.  **`while` Loop**
    * Executes a body of code repeatedly as long as a condition is true.
3.  **`for` Loop**
    * A C-style `for` loop with an initializer, a condition, and an increment clause.

---

### Functions

* Declared with the `fun` keyword: `fun add(a, b) { ... }`
* **First-Class**: Functions are real values that can be stored in variables or passed as arguments.
* **Calling**: Requires parentheses `()`, even for no-argument calls.
* **Returning**: A `return` statement returns a value. If a function finishes without a `return`, it implicitly returns `nil`.
* **Closures**: A function can "close over" and access variables from its surrounding scope, even after the outer function has finished executing.

---

### Classes
* functions inside a class do not have the `fun` keyword used to define a function
* Declared with the `class` keyword: `class Breakfast { ... }`
* **Instances**: Created by calling the class itself like a function: `var instance = Breakfast();`
* **Properties**: Fields can be set and accessed on an instance using dot notation: `instance.field = "value";`
* **`this`**: Inside a method, `this` refers to the instance the method was called on.
* **Initializers**: A method named `init()` acts as a constructor and is called when an instance is created.
* **Inheritance**:
    * Lox supports single inheritance using the `<` operator: `class Brunch < Breakfast { ... }`
    * A subclass inherits all methods from its superclass.
    * `super` is used to call methods on the superclass.

---

### Standard Library

* Extremely minimal.
* **`print`**: A built-in statement to display output.
* **`clock()`**: A built-in function that returns the number of seconds since the program started.
