import { useState } from 'react';
import { appointmentApi } from '../services/api';
import './Users.css';

export default function Appointments() {
    const [userId, setUserId] = useState('');
    const [doctorId, setDoctorId] = useState('');
    const [appointmentTime, setAppointmentTime] = useState('');
    const [appointmentId, setAppointmentId] = useState('');
    const [appointment, setAppointment] = useState(null);
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    const handleBook = async (e) => {
        e.preventDefault();
        if (!userId) {
            setMessage('❌ Please enter your User ID');
            return;
        }
        setLoading(true);
        try {
            const payload = {
                doctorId,
                appointmentTime: new Date(appointmentTime).toISOString()
            };
            const result = await appointmentApi.book(userId, payload);
            setAppointment(result);
            setMessage('✅ Appointment booked successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    const handleConfirm = async () => {
        if (!userId || !appointmentId) {
            setMessage('❌ Please enter User ID and Appointment ID');
            return;
        }
        setLoading(true);
        try {
            const result = await appointmentApi.confirm(userId, appointmentId);
            setAppointment(result);
            setMessage('✅ Appointment confirmed successfully');
        } catch (error) {
            setMessage(`❌ Error: ${error.message}`);
        }
        setLoading(false);
    };

    return (
        <div className="page-container">
            <h1 className="page-title">📅 Appointment Management</h1>

            <div className="content-grid">
                <div className="form-card">
                    <h2>🩺 Book Appointment (Patient)</h2>
                    <form onSubmit={handleBook}>
                        <div className="form-group">
                            <label>Your User ID (Patient)</label>
                            <input
                                type="text"
                                value={userId}
                                onChange={(e) => setUserId(e.target.value)}
                                placeholder="Enter your User ID"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Doctor ID</label>
                            <input
                                type="text"
                                value={doctorId}
                                onChange={(e) => setDoctorId(e.target.value)}
                                placeholder="Enter doctor ID"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Appointment Date & Time</label>
                            <input
                                type="datetime-local"
                                value={appointmentTime}
                                onChange={(e) => setAppointmentTime(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? 'Booking...' : 'Book Appointment'}
                        </button>
                    </form>
                </div>

                <div className="form-card">
                    <h2>✅ Confirm Appointment (Doctor)</h2>
                    <div className="form-group">
                        <label>Your User ID (Doctor)</label>
                        <input
                            type="text"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                            placeholder="Enter your Doctor User ID"
                        />
                    </div>
                    <div className="form-group">
                        <label>Appointment ID</label>
                        <input
                            type="text"
                            value={appointmentId}
                            onChange={(e) => setAppointmentId(e.target.value)}
                            placeholder="Enter appointment ID to confirm"
                        />
                    </div>
                    <button onClick={handleConfirm} className="btn btn-warning" disabled={loading}>
                        {loading ? 'Confirming...' : 'Confirm Appointment'}
                    </button>
                </div>
            </div>

            {message && <div className="message">{message}</div>}

            {appointment && (
                <div className="result-card">
                    <h2>Appointment Details</h2>
                    <div className="user-details">
                        <div className="detail-row">
                            <span className="label">Appointment ID:</span>
                            <span className="value">{appointment.id}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Patient ID:</span>
                            <span className="value">{appointment.patientId}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Doctor ID:</span>
                            <span className="value">{appointment.doctorId}</span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Date & Time:</span>
                            <span className="value">
                                {appointment.appointmentTime
                                    ? new Date(appointment.appointmentTime).toLocaleString()
                                    : 'N/A'}
                            </span>
                        </div>
                        <div className="detail-row">
                            <span className="label">Status:</span>
                            <span className={`value status-badge ${appointment.status?.toLowerCase()}`}>
                                {appointment.status}
                            </span>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
