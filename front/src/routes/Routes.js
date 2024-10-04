import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Dashboard from '../components/Dashboard';
import AdminPanel from '../components/AdminPanel';
import TelegramLogin from '../components/TelegramLogin';

const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<TelegramLogin />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/admin" element={<AdminPanel />} />
            </Routes>
        </Router>
    );
};

export default AppRoutes;
