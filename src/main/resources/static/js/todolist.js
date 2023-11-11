function addTodo() {
    var todoInput = document.getElementById("todoInput");
    var todoList = document.getElementById("todoList");

    if (todoInput.value.trim() === "") {
        alert("할 일을 입력하세요!");
        return;
    }

    var li = document.createElement("li");
    li.innerHTML = todoInput.value + '<button onclick="removeTodo(this)">삭제</button>';
    todoList.appendChild(li);

    todoInput.value = "";
}

function removeTodo(element) {
    var li = element.parentElement;
    li.remove();
}
