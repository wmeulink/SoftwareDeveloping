using System;
using Microsoft.VisualBasic;
using System.Collections;

namespace homeworkThree
{
    /// <summary>
    /// Postfix Calculator
    /// </summary>
    /// 

    class Calculator
    {

        private IStackADT stack = new LinkedStack();

        private IStackADT Stack
        {
            get { return stack; }
        }


        static void Main(string[] args)
        {
            Calculator calc = new Calculator();
            bool calculate = true;
            Console.Write("Postfix Calculator.\nRecognizes these operators: + - * /");
            while (calculate)
            {
                calculate = calc.Calculate();
            }
            Console.Write("Goodbye!");
        }


           private bool Calculate()
        {
            Console.Write("Please enter q to quit\n ");
            string input, output = "";

            input = Console.ReadLine();

            if (input.Trim().ToLower().StartsWith("q"))
            {
                return false;
            }
            try
            {
                output = PostFixHelper(input);
            }
            catch (ArgumentException ex)
            {
                output = ex.Message;
            }
            Console.Write(input + " = " + output);
            return true;
        }


        private string getNextString(string input, double d)
        {
            
            return input;
        }
        private string PostFixHelper(string input)
        {

            if (input == null || input.Equals(""))
            {
                throw new ArgumentException("Null items are not allowed in the stack");
            }

            int count = 0;
            stack.Clear();
            double a, b, c, d;

            
            string[] vars = input.Trim().Split(' ');

            for (int i =0; i<vars.Length; i++)
                {
                if (Double.TryParse(vars[i], out d))
                {
                    Stack.Push(d);
                    count++;
                }
                else
                {
                    if (vars[i].Length > 1)
                    {
                        throw new ArgumentException("Please input a valid string");
                    }
                    if (Stack.IsEmpty())
                    {
                        throw new ArgumentException("Please input a valid input");
                    }
                    b = (double)Stack.Pop();
                    if (Stack.IsEmpty())
                    {
                        throw new ArgumentException("Please input a valid string");
                    }
                    a = (double)Stack.Pop();
                    c = DoOperation(a, b, vars[i], ref count);

                    Stack.Push(c);
                }
                }
            if (count > 1)
            {
                throw new ArgumentException("Invalid input");
            }

            return Stack.Pop().ToString();
        }

        private IEnumerator<int> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        private double DoOperation(double a, double b, string v, ref int stackCount)
        {
            double c = 0.0;
            if (v.Equals("+"))
                c = (a + b);
            else if (v.Equals("-"))
                c = (a - b);
            else if (v.Equals("*"))
                c = (a * b);
            else if (v.Equals("/"))
            {
                try
                {
                    c = (a / b);
                    if (Double.IsNegativeInfinity(c) || Double.IsPositiveInfinity(c))
                        throw new ArgumentException("Can't divide by zero.");
                }
                catch (ArithmeticException e)
                {
                    throw new ArgumentException(e.Message);
                }
            }
            else
                throw new ArgumentException("Improper operator: " + v + ", is not one of +, -, *, or /");

            stackCount--;
            return c;
        }
    } // end class Calculator

    internal interface IEnumerator<T>
    {
    }
}