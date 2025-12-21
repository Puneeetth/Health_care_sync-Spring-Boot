import { useState } from 'react';
import { doctorApi } from '../services/api';
import './Users.css';

export default function Doctors() {
    const [userId, setUserId] = useState('');
    const [formData, setFormData] = useState({
        licenseNumber: '',
        specialization: '',
        yearsOfExperience: '',
        consultationFee: ''
    });
    const [doctor, setDoctor] = useState(null);
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCreate = async (e) => {
        e.preventDefault();
        if (!userId) {
            setMessage('❌ Please enter a User ID first');
            return;
        }
        setLoading(true);
        try {
            const payload = {
                ...formData,
                yearsOfExperience: parseInt(formData.yearsOfExperience) || 0,
                consultationFee: parseFloat(formData.consultationFee) || 0
            };
            const result = await doctorApi.create(userId, payload);
            setDoctor(result);
            setMessage('✅ Doctor profile created successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleGet = async () => {
        if (!userId) {
            setMessage('❌ Please enter a User ID');
            return;
        }
        setLoading(true);
        try {
            const result = await doctorApi.get(userId);
            setDoctor(result);
            setMessage('✅ Doctor profile found');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleUpdate = async () => {
        if (!userId) {
            setMessage('❌ Please enter a User ID');
            return;
        }
        setLoading(true);
        try {
            const payload = {
                ...formData,
                yearsOfExperience: parseInt(formData.yearsOfExperience) || 0,
                consultationFee: parseFloat(formData.consultationFee) || 0
            };
            const result = await doctorApi.update(userId, payload);
            setDoctor(result);
            setMessage('✅ Doctor profile updated successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    return (
        <div className="page-container">
            <h1 className="page-title">👨‍⚕️ Doctor Profile Management</h1>

            <div className="content-grid">
                <div className="form-card">
                    <h2>Doctor Profile</h2>
                    <form onSubmit={handleCreate}>
                        <div className="form-group">
                            <label>User ID (Required)</label>
                            <input
                                type="text"
                                value={userId}
                                onChange={(e) => setUserId(e.target.value)}
                                placeholder="Enter your User ID"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>License Number</label>
                            <input
                                type="text"
                                name="licenseNumber"
                                value={formData.licenseNumber}
                                onChange={handleInputChange}
                                placeholder="e.g., MED-12345"
                            />
                        </div>
                        <div className="form-group">
                            <label>Specialization</label>
                            <input
                                type="text"
                                name="specialization"
                                value={formData.specialization}
                                onChange={handleInputChange}
                                placeholder="e.g., Cardiology"
                            />
                        </div>
                        <div className="form-group">
                            <label>Years of Experience</label>
                            <input
                                type="number"
                                name="yearsOfExperience"
                                value={formData.yearsOfExperience}
                                onChange={handleInputChange}
                                placeholder="e.g., 10"
                                min="0"
                            />
                        </div>
                        <div className="form-group">
                            <label>Consultation Fee ($)</label>
                            <input
                                type="number"
                                name="consultationFee"
                                value={formData.consultationFee}
                                onChange={handleInputChange}
                                placeholder="e.g., 150.00"
                                step="0.01"
                                min="0"
                            />
                        </div>
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? 'Creating...' : 'Create Profile'}
                        </button>
                    </form>
                    <div className="button-group" style={{ marginTop: '1rem' }}>
                        <button onClick={handleGet} className="btn btn-secondary" disabled={loading}>
                            Get Profile
                        </button>
                        <button onClick={handleUpdate} className="btn btn-warning" disabled={loading}>
                            Update Profile
                        </button>
                    </div>
                </div>

                {doctor && (
                    <div className="result-card">
                        <h2>Doctor Details</h2>
                        <div className="user-details">
                            <div className="detail-row">
                                <span className="label">ID:</span>
                                <span className="value">{doctor.id}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">User ID:</span>
                                <span className="value">{doctor.userId}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">License:</span>
                                <span className="value">{doctor.licenseNumber}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Specialization:</span>
                                <span className="value role-badge">{doctor.specialization}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Experience:</span>
                                <span className="value">{doctor.yearsOfExperience} years</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Fee:</span>
                                <span className="value">${doctor.consultationFee}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Status:</span>
                                <span className={`value status-badge ${doctor.status?.toLowerCase()}`}>
                                    {doctor.status}
                                </span>
                            </div>
                        </div>
                    </div>
                )}
            </div>

            {message && <div className="message">{message}</div>}
        </div>
    );
}
