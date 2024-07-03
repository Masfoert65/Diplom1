function deleteProduct(productId) {
    if (confirm("Вы действительно хотите удалить товар?")) {
        // Отправить запрос на удаление товара
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/catalog/delete/" + productId, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Обновить страницу после успешного удаления товара
                location.reload();
            }
        };
        xhr.send();
    }
}
