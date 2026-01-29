import React from 'react'
import ReactDOM from 'react-dom/client'
import axios from 'axios';
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from '../src/componets/App/App.jsx'
import Footer from './componets/Footer/Footer.jsx';
import { AuthProvider } from "./componets/context/AuthContext.jsx";

axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AuthProvider>
    <App />
    <Footer />
    </AuthProvider>
  </StrictMode>
)
