import { Link } from 'react-router-dom';
import './Dashboard.css';

export default function Dashboard() {
    const features = [
        {
            icon: '👤',
            title: 'User Management',
            description: 'Create, view, update and delete users',
            link: '/users',
            gradient: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)'
        },
        {
            icon: '👨‍⚕️',
            title: 'Doctor Profiles',
            description: 'Manage doctor information and specializations',
            link: '/doctors',
            gradient: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)'
        },
        {
            icon: '🏥',
            title: 'Patient Profiles',
            description: 'Track patient details and medical info',
            link: '/patients',
            gradient: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)'
        },
        {
            icon: '📅',
            title: 'Appointments',
            description: 'Book and manage appointments',
            link: '/appointments',
            gradient: 'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)'
        }
    ];

    return (
        <div className="dashboard">
            <header className="dashboard-header">
                <h1>Welcome to Health Management</h1>
                <p>Your integrated healthcare management solution</p>
            </header>

            <section className="features-grid">
                {features.map((feature, index) => (
                    <Link to={feature.link} key={index} className="feature-card" style={{ '--card-gradient': feature.gradient }}>
                        <span className="feature-icon">{feature.icon}</span>
                        <h3>{feature.title}</h3>
                        <p>{feature.description}</p>
                        <span className="arrow">→</span>
                    </Link>
                ))}
            </section>

            <section className="stats-section">
                <div className="stat-card">
                    <span className="stat-icon">📊</span>
                    <div className="stat-info">
                        <h4>Quick Stats</h4>
                        <p>Connect backend to see live data</p>
                    </div>
                </div>
            </section>
        </div>
    );
}
