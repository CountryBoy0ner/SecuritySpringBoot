const token = sessionStorage.getItem('Authorization');
console.log('Retrieved token from sessionStorage:', token); // Проверка токена

if (!token) {
    console.log('Token not found, redirecting to login page');
    window.location.href = '/login';
} else {
    console.log('Token found, making fetch request');
    fetch('/secured', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,  // Токен должен быть здесь
        }
    })
        .then(response => {
            console.log('Response status:', response.status);
            if (response.ok) {
                return response.text();
            } else {
                console.log('Request failed with status:', response.status);
                window.location.href = '/login';
            }
        })
        .then(data => {
            document.body.innerHTML += data;
        })
        .catch(error => {
            console.error('Error fetching secured page:', error);
            window.location.href = '/login';
        });
}
