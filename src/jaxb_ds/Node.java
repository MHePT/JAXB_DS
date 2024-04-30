package JAXB_DS;

public class Node <T>{
    T Data; //Variable to Store Data
    Node Next; //Pointer Points To Next Node
    
    Node(T Data){
        this.Data = Data;
        Next = null;
    }
}