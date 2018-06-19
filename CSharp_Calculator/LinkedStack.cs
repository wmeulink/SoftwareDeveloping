namespace homeworkThree
{
    /// <summary>
    /// Singly-Linked Stack
    /// </summary>
    class LinkedStack : IStackADT
    {
        private Node currNode;

        public LinkedStack()
        {
            currNode = null;
        }

        /// <summary>
        /// Accessor and Mutator for the current node
        /// </summary>
        public Node CurrNode
        {
            get
            {
                return currNode;
            }
            set
            {
                currNode = value;
            }
        }

        /// <summary>
        /// Clears the stack. 
        /// </summary>
        public void Clear()
        {
            this.currNode = null;
        }

        /// <summary>
        /// Checks to see if the stack is empty, 
        /// </summary>
        /// <returns>returns true if the stack is empty, false if it has items in it</returns>
        public bool IsEmpty()
        {
            return this.currNode == null;
        }

        /// <summary>
        /// Peeks at the top item, without removing
        /// </summary>
        /// <returns>The top item</returns>
        public object Peek()
        {
            return IsEmpty() ? null : currNode.Item;
        }

        /// <summary>
        /// Removes the top off the stack
        /// </summary>
        /// <returns>returns the Node that was popped.</returns>
        public object Pop()
        {
            if (IsEmpty())
            {
                return null;
            }
            object temp = currNode.Item;
            currNode = currNode.Next;
            return temp;
        }

        /// <summary>
        ///Push method for our stack, adds the new item if it is not null
        /// </summary>
        /// <param name="item">item that is being added to the stack.</param>
        /// <returns>The item added or null</returns>
        public object Push(object item)
        {
            if (item == null)
            {
                return null;
            }
            Node newNode = new Node(item, CurrNode);
            CurrNode = newNode;
            return item;
        }
    }
}