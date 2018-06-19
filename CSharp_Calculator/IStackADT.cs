using System;

namespace homeworkThree
{

    public interface IStackADT
    {

        Object Push(Object item);


        Object Pop();


        Object Peek();


        bool IsEmpty();


        void Clear();
    }
}
