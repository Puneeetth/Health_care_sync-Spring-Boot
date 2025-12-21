const API_BASE_URL = 'http://localhost:8080';

// User API
export const userApi = {
    create: async (userData) => {
        const response = await fetch(`${API_BASE_URL}/users/create`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData),
        });
        return response.json();
    },

    get: async (id) => {
        const response = await fetch(`${API_BASE_URL}/users/${id}`);
        return response.json();
    },

    update: async (id, userData) => {
        const response = await fetch(`${API_BASE_URL}/users/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData),
        });
        return response.json();
    },

    delete: async (id) => {
        const response = await fetch(`${API_BASE_URL}/users/${id}`, {
            method: 'DELETE',
        });
        return response.text();
    },
};

// Doctor API
export const doctorApi = {
    create: async (userId, doctorData) => {
        const response = await fetch(`${API_BASE_URL}/doctors/me`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId,
            },
            body: JSON.stringify(doctorData),
        });
        return response.json();
    },

    get: async (userId) => {
        const response = await fetch(`${API_BASE_URL}/doctors/me`, {
            headers: { 'X-USER-ID': userId },
        });
        return response.json();
    },

    update: async (userId, doctorData) => {
        const response = await fetch(`${API_BASE_URL}/doctors/me`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId,
            },
            body: JSON.stringify(doctorData),
        });
        return response.json();
    },
};

// Patient API
export const patientApi = {
    create: async (userId, patientData) => {
        const response = await fetch(`${API_BASE_URL}/patients/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId,
            },
            body: JSON.stringify(patientData),
        });
        return response.json();
    },

    get: async (userId) => {
        const response = await fetch(`${API_BASE_URL}/patients/me`, {
            headers: { 'X-USER-ID': userId },
        });
        return response.json();
    },

    update: async (userId, patientData) => {
        const response = await fetch(`${API_BASE_URL}/patients/me`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId,
            },
            body: JSON.stringify(patientData),
        });
        return response.json();
    },
};

// Appointment API
export const appointmentApi = {
    book: async (userId, appointmentData) => {
        const response = await fetch(`${API_BASE_URL}/appointments/book`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId,
            },
            body: JSON.stringify(appointmentData),
        });
        return response.json();
    },

    confirm: async (userId, appointmentId) => {
        const response = await fetch(`${API_BASE_URL}/appointments/${appointmentId}/confirm`, {
            method: 'PATCH',
            headers: { 'X-USER-ID': userId },
        });
        return response.json();
    },
};
