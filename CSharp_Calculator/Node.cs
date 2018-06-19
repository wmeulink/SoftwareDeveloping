namespace homeworkThree
{

    /// <summary>
    /// Node Class for a Singly Linked List
    /// Author Whitney Meulink
    /// Date Modified: October 1st, 2016
    /// </summary>

    class Node
    {
        object item;
        Node next;



        /// <summary>
        /// Node Constructor
        /// This class constructs and initializes the current Node and object item
        /// </summary>
        /// <param name="item"></param>
        /// <param name="next"></param>

        public Node(object item, Node next)
        {
            this.item = item;
            this.next = next;
        }

        /// <summary>
        /// Item Accessor and Mutator Methods
        /// This class accesses and sets the item object
        /// </summary>

        public object Item
        {
            get
            {
                return item;
            }
            set
            {
                item = value;
            }
        }


        /// <summary>
        /// Get Next Node Accessor and Mutator
        /// This Method accesses and sets the next node
        /// </summary>

        public Node Next
        {
            get
            {
                return next;
            }
            set
            {
                next = value;
            }

        }
    }
}
