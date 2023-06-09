# The Problem

https://en.wikipedia.org/wiki/Fibonacci_sequence

**IMPORTANT!!!** use the refactor phase to improve your application design and code quality.

# Requirements

Create an application which allows the user to:
1. Request Fibonacci value for a given position. Input example: { "13" }
2. Request Fibonacci values between 2 positions. Input example: {"2", "4"}
3. Request Fibonacci values up to one position. Input example: {"all", "4"}
4. Request Fibonacci values up to position ordered asc or desc. Input example: {"asc", "4"} or {"desc", "4"},

# Requirements analysis
The application requires to process user input and print the expected values.

**This can be split on:**

1. resolve the values for the given input. LET'S START WITH THIS.
   1. using a loop implementation
   2. using a recursive implementation
   3. using a stream implementation
   4. include inMemory memorization
   5. include inFile memorization
2. process user input and print values.