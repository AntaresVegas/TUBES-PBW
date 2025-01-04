function togglePassword() {
    const passwordInput = document.getElementById('password');
    const toggleText = passwordInput.nextElementSibling;

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleText.textContent = 'Show';
    } else {
        passwordInput.type = 'password';
        toggleText.textContent = 'Hide';
    }
}