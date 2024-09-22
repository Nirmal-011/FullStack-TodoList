import { BrowserRouter, Link, Router } from "react-router-dom";
import ListOfTodo from "./ListOfTodo";

const Header=()=>{
    return(
       <h1 style={{backgroundColor:"black", color:"white", fontFamily:"serif", padding:"1%"}}>Todo Management Application</h1> 
    )
}
export default Header;