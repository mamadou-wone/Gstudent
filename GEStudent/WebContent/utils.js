let selects = document.querySelectorAll("select")
selects.forEach(element => {
    element.addEventListener('click', () => {
        console.log(element.id);
        document.getElementById("user" + element.id).style.display = 'none'
    })
});