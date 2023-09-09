# WheelOfFortune

[Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f5280009-9d15-4886-819d-42a6ba324961/fb5fe73c-ccdd-4a01-87d5-4151b205a29a/Untitled.avif)

- **Description**: A game of guessing a hidden phrase. Players get multiple chances to guess only one character at a time for reviewing the whole hidden phrase. Players win by successfully guess the entire hidden phrase. Players lose by guessing wrong characters and losing all their chances.
- **Development process**:
    - **Process**: Instance methods with data member manipulation instead of Static methods with multiple input arguments.
    - **Bugs**: Type conversion are not so convenient as in Python. I need to look over some extra classes for converting the types.
    - **Concepts**: Java is much more formal and restricted, which feels much safer than using Python. Such as we need to state all the types of variables and types of function returns so that we always know what kind of object we are handling.
    - **algorithm**: StringBuilder is our friend. Just simply replace the char matched using setCharAt() with indexes we found while iterating original phrase.
