"""
user: mal3941 Moisés Lora Pérez
file: FizzBuzz.py
language: python3

"""
def main():
    for i in range(1,100):
        if (i % 5 == 0 and i % 7 == 0):
            print("FizzBuzz")
        elif i % 5 == 0:
            print("Fizz")
        elif i % 7 == 0:
            print("Buzz")

        else:
            print(i)

main()

