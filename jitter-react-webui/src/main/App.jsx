'use strict'
import {StrictMode} from 'react';
import {HomePage} from './pages/home/HomePage';
import {
  BrowserRouter,
  Route,
  Routes,
} from "react-router-dom";

export function App() {
  return (
      <StrictMode>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage/>}/>
          </Routes>
        </BrowserRouter>
      </StrictMode>
  );
}

export default App
