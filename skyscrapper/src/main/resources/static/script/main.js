document.addEventListener('DOMContentLoaded', function(event) {
    let waveEffects = document.querySelectorAll('.hi');
    waveEffects.forEach(function(waveEffect) {
        let text = waveEffect.textContent.trim();

        function animateText() {
            let result = '';
            for (let i = 0; i < text.length; i++) {
                result += `<span style="animation-delay: ${i * 0.1}s">${text[i]}</span>`;
            }
            waveEffect.innerHTML = result;
        }

        animateText();
        setInterval(animateText, text.length * 200); // 반복 간격 설정 (글자 수 * 100ms)
    });
});