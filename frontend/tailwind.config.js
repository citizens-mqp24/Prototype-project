/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        "neon-blue": {
          100:'#039fb8',
          200: '#1360b3',
          300: '#162056'
        },
        "neon-pink": {
          100: '#ff8cac',
          200: '#fb2ead',
          300: '#DC1C9E'
        }
      },
    },
  },
  plugins: [],
}

