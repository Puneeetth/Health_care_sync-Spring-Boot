import { useState } from 'react';
import { userApi } from '../services/api';
import './Users.css';

export default function Users() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        role: 'PATIENT'
    });
    const [userId, setUserId] = useState('');
    const [user, setUser] = useState(null);
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCreate = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            const result = await userApi.create(formData);
            setMessage(`✅ User created with ID: ${result.id}`);
            setUser(result);
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleGet = async () => {
        if (!userId) return;
        setLoading(true);
        try {
            const result = await userApi.get(userId);
            setUser(result);
            setMessage('✅ User found');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleUpdate = async () => {
        if (!userId) return;
        setLoading(true);
        try {
            const result = await userApi.update(userId, formData);
            setUser(result);
            setMessage('✅ User updated successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleDelete = async () => {
        if (!userId) return;
        setLoading(true);
        try {
            await userApi.delete(userId);
            setUser(null);
            setMessage('✅ User deleted successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    return (
        <div className="page-container">
            <h1 className="page-title">👤 User Management</h1>

            <div className="content-grid">
                <div className="form-card">
                    <h2>Create / Update User</h2>
                    <form onSubmit={handleCreate}>
                        <div className="form-group">
                            <label>Name</label>
                            <input
                                type="text"
                                name="name"
                                value={formData.name}
                                onChange={handleInputChange}
                                placeholder="Enter name"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Email</label>
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleInputChange}
                                placeholder="Enter email"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Phone</label>
                            <input
                                type="tel"
                                name="phone"
                                value={formData.phone}
                                onChange={handleInputChange}
                                placeholder="Enter phone"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Role</label>
                            <select name="role" value={formData.role} onChange={handleInputChange}>
                                <option value="PATIENT">Patient</option>
                                <option value="DOCTOR">Doctor</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? 'Creating...' : 'Create User'}
                        </button>
                    </form>
                </div>

                <div className="form-card">
                    <h2>Find / Manage User</h2>
                    <div className="form-group">
                        <label>User ID</label>
                        <input
                            type="text"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                            placeholder="Enter user ID"
                        />
                    </div>
                    <div className="button-group">
                        <button onClick={handleGet} className="btn btn-secondary" disabled={loading}>
                            Get User
                        </button>
                        <button onClick={handleUpdate} className="btn btn-warning" disabled={loading}>
                            Update
                        </button>
                        <button onClick={handleDelete} className="btn btn-danger" disabled={loading}>
                            Delete
                        </button>
                    </div>
                </div>
            </div>

            {message && <div className="message">{message}</div>}

            {user && (
                <div className="result-card">
                    <h2>User Details</h2>
                    <div className="user-details">
                        <div className="detail-row">
                            <span className="label">ID:</span>
                            <span className="value">{user.id}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Name:</span>
                            <span className="value">{user.name}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Email:</span>
                            <span className="value">{user.email}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Phone:</span>
                            <span className="value">{user.phone}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Role:</span>
                            <span className="value role-badge">{user.role}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Status:</span>
                            <span className={`value status-badge ${user.status?.toLowerCase()}`}>
                                {user.status}
                            </span>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
