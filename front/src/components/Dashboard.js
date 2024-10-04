import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        axios.get('/api/users/me')
            .then(response => setUserData(response.data))
            .catch(error => console.error(error));
    }, []);

    if (!userData) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>Личный кабинет</h1>
            <p>ID: {userData.telegramId}</p>
            <p>Имя: {userData.firstName}</p>
            <p>Фамилия: {userData.lastName}</p>
            <p>Username: {userData.username}</p>
            <p>Номер телефона: {userData.phoneNumber}</p>
            <p>Язык: {userData.languageCode}</p>
            <img src={userData.profilePhoto} alt="Profile" />
        </div>
    );
};

export default Dashboard;
