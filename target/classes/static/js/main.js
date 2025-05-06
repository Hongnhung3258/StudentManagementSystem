// Main JavaScript file for University Management System

// Initialize Feather icons (used in sidebar)
document.addEventListener('DOMContentLoaded', function() {
    if (typeof feather !== 'undefined') {
        feather.replace();
    }
});

// Confirmation dialog for delete actions
function confirmDelete(url, itemName) {
    if (confirm('Are you sure you want to delete ' + itemName + '?')) {
        window.location.href = url;
    }
}

// Toggle password visibility in forms
function togglePasswordVisibility(inputId, buttonId) {
    const passwordInput = document.getElementById(inputId);
    const toggleButton = document.getElementById(buttonId);
    
    if (passwordInput && toggleButton) {
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            toggleButton.innerHTML = '<i class="feather-icon" data-feather="eye-off"></i>';
        } else {
            passwordInput.type = 'password';
            toggleButton.innerHTML = '<i class="feather-icon" data-feather="eye"></i>';
        }
        
        if (typeof feather !== 'undefined') {
            feather.replace();
        }
    }
}

// Initialize datepickers if jQuery UI is available
document.addEventListener('DOMContentLoaded', function() {
    if (typeof jQuery !== 'undefined' && jQuery.fn.datepicker) {
        jQuery('.datepicker').datepicker({
            dateFormat: 'yy-mm-dd',
            changeMonth: true,
            changeYear: true,
            yearRange: '1950:2030'
        });
    }
});

// Sidebar collapsing for mobile
document.addEventListener('DOMContentLoaded', function() {
    const toggleBtn = document.getElementById('sidebarToggle');
    const sidebar = document.getElementById('sidebar');
    
    if (toggleBtn && sidebar) {
        toggleBtn.addEventListener('click', function() {
            sidebar.classList.toggle('collapsed');
        });
    }
});