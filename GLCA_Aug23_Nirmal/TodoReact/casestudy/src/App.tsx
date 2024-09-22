import { BrowserRouter, Route, Switch } from "react-router-dom";
import ListOfTodo from "./ListOfTodo";
import TodoComponent from "./TodoComponent";
import Header from "./Header";
import Footer from "./Footer";

const App=()=>{
    return(<>
        <Header/>
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={ListOfTodo}></Route>
            <Route path="/add-todo" component={TodoComponent}></Route>
            <Route path="/update/:id" component={TodoComponent}></Route>
        </Switch>
    </BrowserRouter>
    <Footer/>
    </>)
}
export default App;