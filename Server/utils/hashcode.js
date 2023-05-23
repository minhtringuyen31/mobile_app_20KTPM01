// hashUtils.js

// Public function to calculate the hash code
function hashCode(str) {
    let hash = 0;

    if (!str || str.length === 0) {
        return hash;
    }

    for (let i = 0; i < str.length; i++) {
        const char = str.charCodeAt(i);
        hash = (hash << 5) - hash + char;
        hash = hash & hash; // Convert to 32bit integer
    }

    return hash;
}

export default {
    hashCode
};