document.getElementById('registrationForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // Останавливаем стандартное поведение формы

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    try {
        const response = await fetch('/api/auth/reregistration', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                userName: username,
                email: email,
                password: password,
                confirmPassword: confirmPassword
            }),
        });

        const responseDisplay = document.getElementById('responseDisplay');

        if (response.ok) {
            const data = await response.json();
            responseDisplay.style.color = 'green';
            responseDisplay.textContent = `Регистрация успешна! Ваше имя: ${data.userName}, ваш email: ${data.email}`;
        } else {
            const error = await response.json(); // Получаем JSON-ошибку с сервера
            responseDisplay.style.color = 'red';
            responseDisplay.textContent = error.message; // Используем только поле "message"
        }
    } catch (error) {
        console.error('Ошибка:', error);
        alert('Произошла ошибка при отправке запроса.');
    }
});
