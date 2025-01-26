document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        });

        const responseDisplay = document.getElementById('responseDisplay');

        if (response.ok) {
            const data = await response.json();
            const token = data.token;
                console.log(token);


            sessionStorage.setItem('Authorization', token);
            //localStorage.setItem('Authorization', token);
            //document.cookie = `Authorization=${token}; path=/; Secure; HttpOnly`;

            responseDisplay.style.color = 'green';
            responseDisplay.textContent = 'Вход выполнен успешно!';
        } else {
            const error = await response.json();
            responseDisplay.style.color = 'red';
            responseDisplay.textContent = error.message;
        }
        //window.location.href = '/secured';

    } catch (error) {
        console.error('Ошибка:', error);
        alert('Произошла ошибка при отправке запроса.');
    }
});
