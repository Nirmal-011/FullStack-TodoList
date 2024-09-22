import { title } from "process";
import { FormEvent, useEffect, useState } from "react";
import { Button, Card, Container, Form } from "react-bootstrap";
import { createTodo, getTodo, updateTodo } from "./Service/TodoService";
import { useHistory, useParams } from "react-router-dom";

type todo={
  id:number,
  title:string,
  description:string,
  taskCompleted:boolean
}

interface RouteParam{
  id:string;
}
const TodoComponent=()=>{

  let[title,setTitle]=useState<string>("");
  let[description,setDescription]=useState<string>("");
  let[taskCompleted,setTaskCompleted]=useState<boolean>(false);

  let navigator=useHistory();

  const{id}=useParams<RouteParam>();
  
  function getTodoById(id:number){
    getTodo(Number(id))
    .then((response)=>{
      setTitle(response.data.title);
      setDescription(response.data.description);
      setTaskCompleted(response.data.completed==true);
    }).catch((err)=>console.log(err))
  }

  useEffect(()=>{
    if(id){
      getTodoById(Number(id));
    }
  },[id])
  function saveOrUpdate(e:FormEvent){
    e.preventDefault();
    let todo={title,description,taskCompleted};
    if(id){
      updateTodo(Number(id),todo).then((response)=>{
      console.log(response.data)
      navigator.push("/")
      }).catch((err)=>console.log(err))
    }
    else{
      createTodo(todo)
      .then((response)=>{
        console.log(response.data);
        navigator.push("/");
      })
      .catch((err)=>console.log(err));
    }

  }

  function changeHeading(){
    if(id){
        return(<h2 style={{textAlign:"center", textDecoration:"underline"}}>Update Todo</h2>)
    }
    else{
        return(<h2 style={{textAlign:"center", textDecoration:"underline" }}>Add Todo</h2>)
    }
    }


    return(<>
    <div className="card col-md-6 offset-md-3 offset-mid-3" style={{marginTop:"70px", padding:"1%"}}>
     {changeHeading()}
    <Form onSubmit={(e)=>saveOrUpdate(e)}> 
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Todo Title</Form.Label>
        <Form.Control type="text" value={title}   onChange={(e)=>setTitle(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Description</Form.Label>
        <Form.Control type="text"
          value={description} 
          onChange={(e)=>setDescription(e.target.value)}/>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Task Complete</Form.Label>
          <select 
            className="form-control"
            value={taskCompleted ? "true" : "false"}
            onChange={(e) => setTaskCompleted(e.target.value==="true")}
          >
            <option value="false">No</option>
             <option value="true">Yes</option>   
          </select>
      </Form.Group>
      
      <Button variant="success" type="submit">
        Submit
      </Button>
    </Form>
    </div>
    </>)
}
export default TodoComponent;