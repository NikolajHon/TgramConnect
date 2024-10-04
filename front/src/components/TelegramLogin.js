import React, { useEffect } from 'react';

const TelegramLogin = () => {
    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://telegram.org/js/telegram-widget.js?15';
        script.async = true;
        script.setAttribute('data-telegram-login', 'TgramAuthBot');
        script.setAttribute('data-size', 'large');
        script.setAttribute('data-auth-url', 'https://mytestsite.loca.lt/api/auth/telegram');
        script.setAttribute('data-request-access', 'write');

        document.getElementById('telegram-login-container').appendChild(script);
    }, []);

    return (
        <div>
            <h1>Авторизация через Telegram</h1>
            <div id="telegram-login-container"></div>
        </div>
    );
};

export default TelegramLogin;
