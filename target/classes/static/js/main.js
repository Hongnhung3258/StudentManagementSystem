/**
 * Confirms deletion of an item
 * @param {string} url - The deletion URL
 * @param {string} itemName - The name of the item to be deleted
 */
function confirmDelete(url, itemName) {
    if (confirm('Bạn có chắc chắn muốn xóa ' + itemName + ' không?')) {
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
    const toggleButton = document.getElementById(buttonId);
    
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleButton.innerHTML = '<span data-feather="eye-off"></span>';
    } else {
        passwordInput.type = 'password';
        toggleButton.innerHTML = '<span data-feather="eye"></span>';
    }
    
    // Re-initialize feather icons
    feather.replace();
}