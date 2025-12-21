import { useState } from 'react';
import { patientApi } from '../services/api';
import './Users.css';

export default function Patients() {
    const [userId, setUserId] = useState('');
    const [formData, setFormData] = useState({
        dateOfBirth: '',
        gender: '',
        bloodGroup: '',
        emergencyContact: '',
        address: ''
    });
    const [patient, setPatient] = useState(null);
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
                dateOfBirth: formData.dateOfBirth ? new Date(formData.dateOfBirth).toISOString() : null
            };
            const result = await patientApi.create(userId, payload);
            setPatient(result);
            setMessage('✅ Patient profile created successfully');
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
            const result = await patientApi.get(userId);
            setPatient(result);
            setMessage('✅ Patient profile found');
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
                dateOfBirth: formData.dateOfBirth ? new Date(formData.dateOfBirth).toISOString() : null
            };
            const result = await patientApi.update(userId, payload);
            setPatient(result);
            setMessage('✅ Patient profile updated successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    return (
        <div className="page-container">
            <h1 className="page-title">🏥 Patient Profile Management</h1>

            <div className="content-grid">
                <div className="form-card">
                    <h2>Patient Profile</h2>
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
                            <label>Date of Birth</label>
                            <input
                                type="date"
                                name="dateOfBirth"
                                value={formData.dateOfBirth}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>Gender</label>
                            <select name="gender" value={formData.gender} onChange={handleInputChange}>
                                <option value="">Select Gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Blood Group</label>
                            <select name="bloodGroup" value={formData.bloodGroup} onChange={handleInputChange}>
                                <option value="">Select Blood Group</option>
                                <option value="A+">A+</option>
                                <option value="A-">A-</option>
                                <option value="B+">B+</option>
                                <option value="B-">B-</option>
                                <option value="AB+">AB+</option>
                                <option value="AB-">AB-</option>
                                <option value="O+">O+</option>
                                <option value="O-">O-</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Emergency Contact</label>
                            <input
                                type="tel"
                                name="emergencyContact"
                                value={formData.emergencyContact}
                                onChange={handleInputChange}
                                placeholder="Emergency contact number"
                            />
                        </div>
                        <div className="form-group">
                            <label>Address</label>
                            <textarea
                                name="address"
                                value={formData.address}
                                onChange={handleInputChange}
                                placeholder="Enter address"
                                rows="3"
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

                {patient && (
                    <div className="result-card">
                        <h2>Patient Details</h2>
                        <div className="user-details">
                            <div className="detail-row">
                                <span className="label">ID:</span>
                                <span className="value">{patient.id}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">User ID:</span>
                                <span className="value">{patient.userId}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Date of Birth:</span>
                                <span className="value">
                                    {patient.dateOfBirth ? new Date(patient.dateOfBirth).toLocaleDateString() : 'N/A'}
                                </span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Gender:</span>
                                <span className="value">{patient.gender}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Blood Group:</span>
                                <span className="value role-badge">{patient.bloodGroup}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Emergency Contact:</span>
                                <span className="value">{patient.emergencyContact}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Address:</span>
                                <span className="value">{patient.address}</span>
                            </div>
                            <div className="detail-row">
                                <span className="label">Status:</span>
                                <span className={`value status-badge ${patient.status?.toLowerCase()}`}>
                                    {patient.status}
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
