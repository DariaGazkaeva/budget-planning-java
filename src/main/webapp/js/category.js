document.addEventListener('DOMContentLoaded', () => {
    const createCategoryWidget = document.querySelector('.create-category-widget');
    const createCategoryForm = createCategoryWidget.querySelector("form");
    const createCategoryWidgetButton = document.querySelector(".create-category-widget__button");
    const categorySelect = document.querySelector("select#categorySelect");
    const deleteCategoryWidget = document.querySelector(".delete-category-widget");
    const deleteCategoryWidgetButton = document.querySelector(".delete-category-widget__button");

    createCategoryWidgetButton.addEventListener('click', () => {
        createCategoryWidget.classList.toggle("display-none");
    });

    deleteCategoryWidgetButton.addEventListener('click', () => {
        deleteCategoryWidget.classList.toggle('display-none');
    })

    createCategoryForm.addEventListener("submit", (event) => {
        event.preventDefault();
        let incomeSelect = createCategoryForm.querySelector("select#categoryIncome");
        let income = incomeSelect.options[incomeSelect.selectedIndex].value;
        let name = createCategoryForm.querySelector("input#categoryName").value;

        let request = `income=${income}&name=${name}`;

        fetch(createCategoryForm.action, {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'application/json'
            },
            body: request
        }).then(response => response.json())
            .then(data => {
                console.log(JSON.stringify(data));

                let option = `<option value="${data.id}">${name}</option>`;
                categorySelect.insertAdjacentHTML('beforeend', option);

                let li = `<li><a class="delete-category-widget" href="/another/delete-category/${data.id}">${name}</a></li>`;
                deleteCategoryWidget.querySelector("ul").insertAdjacentHTML('beforeend', li);
            });
    })



})
