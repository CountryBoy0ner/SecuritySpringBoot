const token = sessionStorage.getItem('Authorization'); // Или localStorage

if (!token) {
    console.log('Токен отсутствует. Перенаправление на страницу входа.');
    window.location.href = '/login';
} else {
    fetch('/secured', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                console.error('Доступ запрещён:', response.status);
                window.location.href = '/login';
            }
        })
        .then(data => {
            document.getElementById('securedContent').innerHTML = data;
        })
        .catch(error => console.error('Ошибка запроса:', error));
}
