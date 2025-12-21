import { NavLink } from 'react-router-dom';
import './Navbar.css';

export default function Navbar() {
    return (
        <nav className="navbar">
            <div className="navbar-brand">
                <span className="logo">🏥</span>
                <h1>Health Management</h1>
            </div>
            <ul className="nav-links">
                <li>
                    <NavLink to="/" className={({ isActive }) => isActive ? 'active' : ''}>
                        Dashboard
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/users" className={({ isActive }) => isActive ? 'active' : ''}>
                        Users
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/doctors" className={({ isActive }) => isActive ? 'active' : ''}>
                        Doctors
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/patients" className={({ isActive }) => isActive ? 'active' : ''}>
                        Patients
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/appointments" className={({ isActive }) => isActive ? 'active' : ''}>
                        Appointments
                    </NavLink>
                </li>
            </ul>
        </nav>
    );
}
