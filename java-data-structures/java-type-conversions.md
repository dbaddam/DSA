# Java Type Conversions — Interview Cheatsheet

## Char ↔ Integer

```java
// char digit → int
char c = '7';
int n = c - '0';                        // 7 ← fast, idiomatic
int n = Character.getNumericValue(c);   // 7 ← cleaner, readable

// int → char digit
int n = 7;
char c = (char)(n + '0');              // '7'
char c = Character.forDigit(n, 10);   // '7', 10 = decimal base

// char letter → int (a=0, b=1, ...)
char c = 'e';
int n = c - 'a';   // 4 (lowercase)
int n = c - 'A';   // 4 (uppercase)

// int → char letter
int n = 4;
char c = (char)(n + 'a');  // 'e'
char c = (char)(n + 'A');  // 'E'
```

---

## String ↔ Integer

```java
// String → int
int n = Integer.parseInt("123");   // 123 ← prefer this
int n = Integer.valueOf("123");    // 123 (returns Integer, auto-unboxed)

// int → String
String s = String.valueOf(123);    // "123" ← prefer this
String s = Integer.toString(123);  // "123"
String s = "" + 123;               // "123" ← works but hacky, avoid
```

---

## String ↔ Char Array

```java
// String → char array
char[] arr = "hello".toCharArray();

// char array → String
String s = new String(arr);
String s = String.valueOf(arr);
```

---

## String ↔ StringBuilder

```java
// String → StringBuilder
StringBuilder sb = new StringBuilder("hello");
sb.append("world");
sb.append(123);          // can append ints directly
sb.deleteCharAt(i);      // delete char at index
sb.reverse();            // reverse in place
sb.insert(i, 'x');       // insert at index

// StringBuilder → String
String s = sb.toString();
```

---

## Character Utility Methods

```java
Character.isDigit(c)          // c is 0-9
Character.isLetter(c)         // c is a-z or A-Z
Character.isLetterOrDigit(c)  // c is alphanumeric
Character.isAlphabetic(c)     // c is a letter (includes unicode)
Character.isUpperCase(c)      // c is A-Z
Character.isLowerCase(c)      // c is a-z
Character.toUpperCase(c)      // 'a' → 'A'
Character.toLowerCase(c)      // 'A' → 'a'
Character.getNumericValue(c)  // '7' → 7
```

---

## Quick Reference Card

| From | To | Use |
|---|---|---|
| char digit | int | `c - '0'` or `Character.getNumericValue(c)` |
| int | char digit | `(char)(n + '0')` |
| char letter | int (a=0) | `c - 'a'` or `c - 'A'` |
| int | char letter | `(char)(n + 'a')` |
| String | int | `Integer.parseInt(s)` |
| int | String | `String.valueOf(n)` |
| String | char[] | `s.toCharArray()` |
| char[] | String | `new String(arr)` |
| String | StringBuilder | `new StringBuilder(s)` |
| StringBuilder | String | `sb.toString()` |
| substring | int | `Integer.parseInt(s.substring(i, j))` |