/**
 * Confirms deletion of an item
 * @param {string} url - The deletion URL
 * @param {string} itemName - The name of the item to be deleted
 */
function confirmDelete(url, itemName) {
    if (confirm(`Bạn có chắc chắn muốn xóa ${itemName} này không?`)) {
        window.location.href = url;
    }
}

/**
 * Toggles password visibility
 * @param {string} inputId - The password input ID
 * @param {string} buttonId - The toggle button ID
 */
function togglePasswordVisibility(inputId, buttonId) {
    const passwordInput = document.getElementById(inputId);
    const toggleIcon = document.getElementById(buttonId);
    
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleIcon.innerHTML = `
            <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94"></path>
            <path d="M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19"></path>
            <line x1="1" y1="1" x2="23" y2="23"></line>
        `;
    } else {
        passwordInput.type = 'password';
        toggleIcon.innerHTML = `
            <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
            <circle cx="12" cy="12" r="3"></circle>
        `;
    }
}